package com.venly.venly.controllers;

import com.venly.venly.dtos.WordDto;
import com.venly.venly.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/word")
public class WordController {

    @Autowired
    private WordService wordService;

    @PostMapping("/create-word-relation")
    public ResponseEntity<?> createWordAndRelationShip(@Valid  @RequestBody  WordDto wordDto){
        return wordService.createWordAndRelation(wordDto);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getGetAll(){
        return wordService.getAllWordsWithRelationShip();
    }
}
