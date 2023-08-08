package com.venly.venly.controllers;

import com.venly.venly.dtos.WordDto;
import com.venly.venly.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class WordController {

    @Autowired
    private WordService wordService;

    @PostMapping("/create-word-relation")
    public ResponseEntity<?> createWordAndRelationShip(@Valid  @RequestBody  WordDto wordDto){
        return wordService.createWordAndRelation(wordDto);
    }
}
