package com.venly.venly.repositories;

import com.venly.venly.entities.WordRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRelationRepo extends CrudRepository<WordRelation,Long> {

    Optional<WordRelation> findFirstByFirstWordIdAndSecondWordIdAndRelation(Long firstWord, Long secodWord, String relation);


    List<WordRelation> findAllByRelation(String relation);

    WordRelation findByFirstWordId(Long sourceWord);
    WordRelation findBySecondWordId( Long targetWord);

}