package com.example.mytranslator.repositories;

import com.example.mytranslator.models.Definition;
import com.example.mytranslator.models.Word;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WordTranslatorRepository {
    private Gson gson = new Gson();

    public String translateWord(String word, String language){
        String fileName = "src/main/resources/translations/" +  language + "/"  + word + ".json";
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            return wordModel.toString();
        } catch (Exception e) {
            return "word not found";
        }
    }

    public boolean addWord(Word word, String language){
        String fileName = "src/main/resources/translations/" +  language + "/"  + word.word + ".json";
        try {
            Writer writer = new FileWriter(fileName);
            gson.toJson(word, writer);
            writer.close();
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteWord(String word, String language){
        String fileName = "src/main/resources/translations/" +  language + "/"  + word + ".json";
        try {
            File file = new File(fileName);
            file.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addDefinitionForWord(String word, String language, Definition definition){
        String fileName = "src/main/resources/translations/" +  language + "/"  + word + ".json";
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            wordModel.definitions.add(definition);//todo
            try {
                Writer writer = new FileWriter(fileName);
                gson.toJson(wordModel, writer);
                writer.close();
            } catch (Exception e){
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean removeWordDefinition(String word, String language, String dictionary){
        String fileName = "src/main/resources/translations/" +  language + "/"  + word + ".json";
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            wordModel.definitions.remove(dictionary);
            try {
                Writer writer = new FileWriter(fileName);
                gson.toJson(wordModel, writer);
                writer.close();
            } catch (Exception e){
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Definition> getWordDefinitions(String word, String language){
        String fileName = "src/main/resources/translations/" +  language + "/"  + word + ".json";
        ArrayList<Definition> definitionsArr = new ArrayList<Definition>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            try {
                for(Definition  definition : wordModel.definitions) {
                    definitionsArr.add(definition);
                }
                return definitionsArr;
            }
            catch (Exception e) {
                return definitionsArr;
            }
        }
        catch (Exception e) {
            return definitionsArr;
        }
    }

    public String translateSentence(String sentence, String language){
        String translation = new String();
        String[] words = sentence.split(" ");
        try{
            for (String word: words){
                String fileName = "src/main/resources/translations/" +  language + "/"  + word + ".json";
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
                Word wordModel = gson.fromJson(reader, Word.class);
                translation += " " + wordModel.word.toString();
            }
            return translation;
        }
        catch (Exception e){
            return "Words not found";
        }
    }

    public String exportDictionary(String word, String language){
        String fileName = "src/main/resources/translations/" +  language + "/"  + word + ".json";
        try {
            File jsonDictionary = new File("src/main/resources/data/" + word + "_dictionary.json");
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            FileWriter writer = new FileWriter (jsonDictionary);
            writer.write(wordModel.definitions.toString());
            jsonDictionary.createNewFile();
            writer.close();
            return "Dictionary created";
        } catch (Exception e) {
            return "Dictionary not created";
        }
    }
}
