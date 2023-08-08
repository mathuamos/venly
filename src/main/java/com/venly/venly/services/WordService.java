package com.venly.venly.services;


import com.venly.venly.dtos.ResponseModel;
import com.venly.venly.dtos.WordDto;
import com.venly.venly.entities.WordRelation;
import com.venly.venly.entities.Words;
import com.venly.venly.repositories.WordRelationRepo;
import com.venly.venly.repositories.WordRepo;
import com.venly.venly.utils.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
public class WordService {

    @Autowired
    private WordRepo wordRepo;

    @Autowired
    private WordRelationRepo wordRelationRepo;

    //create word and relation services
    public ResponseEntity<?> createWordAndRelation(WordDto wordDto) {
        Words firstByWord = wordRepo.findFirstByWord(wordDto.getFirstWord());
        Words secondWord = wordRepo.findFirstByWord(wordDto.getSecondWord());

        if (null == firstByWord) {
            firstByWord = new Words();
            firstByWord.setWord(wordDto.getFirstWord().trim().toLowerCase());
            firstByWord = wordRepo.save(firstByWord);
        }

        if (null == secondWord) {
            secondWord = new Words();
            secondWord.setWord(wordDto.getSecondWord().trim().toLowerCase());
            secondWord = wordRepo.save(secondWord);
        }

        Optional<WordRelation> wordRelationCheck = wordRelationRepo.findFirstByFirstWordIdAndSecondWordIdAndRelation(firstByWord.getId(), secondWord.getId(), wordDto.getRelation());
        if (wordRelationCheck.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseModel(StatusCode.CONFLICT.getCode(), StatusCode.CONFLICT.getDescription()));


        wordRelationCheck = wordRelationRepo.findFirstByFirstWordIdAndSecondWordIdAndRelation(secondWord.getId(), firstByWord.getId(), wordDto.getRelation());
        if (wordRelationCheck.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseModel(StatusCode.CONFLICTINVERSE.getCode(), StatusCode.CONFLICTINVERSE.getDescription()));



        WordRelation wordRelation = new WordRelation();
        wordRelation.setRelation(wordDto.getRelation().trim().toLowerCase());
        wordRelation.setFirstWordId(firstByWord.getId());
        wordRelation.setSecondWordId(secondWord.getId());
        wordRelationRepo.save(wordRelation);

        return ResponseEntity.ok(new ResponseModel(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getDescription()));
    }



    //get words with thier relation services
    public ResponseEntity<?> getAllWordsWithRelationShip() {
        Iterable<WordRelation> wordRelations=wordRelationRepo.findAll();
        List<String> words=new ArrayList<>();

        wordRelations.forEach(node->{
            words.add(node.getSecondWordsLink().getWord()+" "+node.getFirstWordsLink().getWord()+" "+node.getRelation() + " no");
        });


        wordRelations.forEach(node->{
            words.add(node.getFirstWordsLink().getWord()+" "+node.getSecondWordsLink().getWord()+" "+node.getRelation() + " yes");
        });
        return ResponseEntity.ok(words);
    }

    //filter words by relation
    public ResponseEntity<?> getWordByRelationShip(String relation) {
        List<String> words=new ArrayList<>();
        List<WordRelation> wordRelations=wordRelationRepo.findAllByRelation(relation);
        wordRelations.forEach(node->{
            words.add(node.getFirstWordsLink().getWord()+" "+node.getSecondWordsLink().getWord()+" "+node.getRelation());
        });
        return ResponseEntity.ok(words);

    }

    //searchPath services
    public String findPath(String sourceWord, String targetWord) {
        Words sourceWords = wordRepo.findFirstByWord(sourceWord);
        Words targetWords = wordRepo.findFirstByWord(targetWord);

        WordRelation sourceRelation = findRelation(sourceWords);
        WordRelation targetRelation = findRelation(targetWords);

        if (sourceRelation != null && targetRelation != null) {
            String sourceRelationString = sourceRelation.getRelation();
            String destinationRelationString = targetRelation.getRelation();

            String sourceOtherWord = getOtherWord(sourceWords, sourceRelation);
            String destinationOtherWord = getOtherWord(targetWords, targetRelation);

            if (sourceOtherWord.equalsIgnoreCase(destinationOtherWord)) {
                return formatPath(sourceWord, sourceRelationString, sourceOtherWord, destinationRelationString, targetWord);
            }
        }

        return "No valid path found.";
    }

    private WordRelation findRelation(Words words) {
        WordRelation relation = wordRelationRepo.findByFirstWordId(words.getId());
        if (relation == null) {
            relation = wordRelationRepo.findBySecondWordId(words.getId());
        }
        return relation;
    }

    private String getOtherWord(Words words, WordRelation relation) {
        return words.getWord().equalsIgnoreCase(relation.getFirstWordsLink().getWord())
                ? relation.getSecondWordsLink().getWord()
                : relation.getFirstWordsLink().getWord();
    }

    private String formatPath(String sourceWord, String sourceRelation, String sourceOtherWord, String destinationRelation, String targetWord) {
        return String.format("%s ==(%s)=> %s ==(%s)=> %s", sourceWord, sourceRelation, sourceOtherWord, destinationRelation, targetWord);
    }


}