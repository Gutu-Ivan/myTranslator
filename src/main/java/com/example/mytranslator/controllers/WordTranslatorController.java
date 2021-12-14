package com.example.mytranslator.controllers;

import com.example.mytranslator.models.Definition;
import com.example.mytranslator.models.Word;
import com.example.mytranslator.repositories.WordTranslatorRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class WordTranslatorController {

    private WordTranslatorRepository wordTranslatorRepository = new WordTranslatorRepository();

    @GetMapping(path = "translate/word/{fromLanguage}/{toLanguage}/{word}")
    public String translateWord(@PathVariable String word, String fromLanguage, String toLanguage){
        return wordTranslatorRepository.translateWord(word, fromLanguage, toLanguage);
    }

    @PostMapping(path = "translate/word/{language}")
    public boolean addWord(@RequestBody Word word, @PathVariable String language){
        return wordTranslatorRepository.addWord(word, language);
    }

    @DeleteMapping(path = "translate/word/{language}/{word}")
    public boolean deleteWord(@PathVariable String word, @PathVariable String language){
        return wordTranslatorRepository.deleteWord(word, language);
    }

    @PostMapping(path = "translate/word/{language}/{word}")
    public boolean addDefinitionForWord(@PathVariable String word, @PathVariable String language, @RequestBody Definition definition){
        return wordTranslatorRepository.addDefinitionForWord(word, language, definition);
    }

    @DeleteMapping(path = "translate/word/{language}/{word}/{dictType}")
    public boolean removeWordDefinition(@PathVariable String word, @PathVariable String language, @RequestBody String dictionary){
        return wordTranslatorRepository.removeWordDefinition(word, language, dictionary);
    }

//    @GetMapping(path = "translate/word/{fromLanguage}/{toLanguage}/{sentence}")
//    public String translateSentence(@PathVariable String sentence, String fromLanguage, String toLanguage){
//        return wordTranslatorRepository.translateSentence(sentence, fromLanguage, toLanguage);
//    }
}
