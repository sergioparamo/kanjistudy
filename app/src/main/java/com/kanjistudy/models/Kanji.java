package com.kanjistudy.models;

import com.google.gson.annotations.SerializedName;

public class Kanji {

    private String  kanji;


    public Kanji(String kanji) {
        this.kanji = kanji;

    }

    public String  getKanji() {
        return kanji;
    }

    public void setKanji(String  kanji) {
        this.kanji = kanji;
    }


}
