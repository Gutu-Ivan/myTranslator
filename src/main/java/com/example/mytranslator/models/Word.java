package com.example.mytranslator.models;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Word {
    public String word;
    public String word_en;
    public String type;
    public ArrayList<String> singular;
    public ArrayList<String> plural;
    public ArrayList<Definition> definitions;

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<Definition> definitions) {
        this.definitions = definitions;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
