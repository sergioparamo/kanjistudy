package com.kanjistudy.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Kana")
public class Kana {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "hiragana_id")
    int kanaId;
    String hiragana;
    String katakana;
    String romaji;

    public int getPronunciationId() {
        return pronunciationId;
    }

    int pronunciationId;


    //According to the vowel and consonant
    String vowel;
    String consonant;

    //We divide the hiraganas between 4 types. Monographs (gojūon), Digraphs (yōon), Diacritics (gojūon with (han)dakuten) and Digraphs with diacritics (yōon with (han)dakuten).
    String type;

    public Kana(String hiragana, String katakana, String romaji, String vowel, String consonant, String type, int pronunciationId) {
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.romaji = romaji;
        this.vowel = vowel;
        this.consonant = consonant;
        this.type = type;
        this.pronunciationId = pronunciationId;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public int getKanaId() {
        return kanaId;
    }

    public void setKanaId(int kanaId) {
        this.kanaId = kanaId;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getVowel() {
        return vowel;
    }

    public void setVowel(String vowel) {
        this.vowel = vowel;
    }

    public String getConsonant() {
        return consonant;
    }

    public void setConsonant(String consonant) {
        this.consonant = consonant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getKatakana() {
        return katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }
}