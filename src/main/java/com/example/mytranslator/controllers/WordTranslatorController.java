package com.example.mytranslator.controllers;

import com.example.mytranslator.models.Definition;
import com.example.mytranslator.models.Word;
import com.example.mytranslator.repositories.WordTranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;

@RestController
public class WordTranslatorController {

    private WordTranslatorRepository wordTranslatorRepository = new WordTranslatorRepository();

    @GetMapping(path = "translate/word/{language}/{word}")
    public String translateWord(@PathVariable String word, @PathVariable String language){
        return wordTranslatorRepository.translateWord(word, language);
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

    @DeleteMapping(path = "translate/word/{language}/{word}/{dictionary}")
    public boolean removeWordDefinition(@PathVariable String word, @PathVariable String language, @RequestBody String dictionary){
        return wordTranslatorRepository.removeWordDefinition(word, language, dictionary);
    }

    @GetMapping(path = "translate/word/definitions/{language}/{word}")
    public ArrayList<Definition> getWordDefinitions(@PathVariable String word, @PathVariable String language){
        return wordTranslatorRepository.getWordDefinitions(word, language);
    }

    @GetMapping(path = "translate/sentence/{language}/{sentence}")
    public String translateSentence(@PathVariable String sentence, @PathVariable String language){
        return wordTranslatorRepository.translateSentence(sentence, language);
    }

    @GetMapping(path = "translate/export/dictionary/{language}/{word}")
    public String exportDictionary(@PathVariable String word, @PathVariable String language){
        return wordTranslatorRepository.exportDictionary(word, language);
    }
}
