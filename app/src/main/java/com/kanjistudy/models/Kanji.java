package com.kanjistudy.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Kanji")
public class Kanji {
    @Override
    public String toString() {
        return "KanjiDb{" +
                "kanjiId=" + kanjiId +
                ", kanji='" + kanji + '\'' +
                ", nivel='" + level + '\'' +
                '}';
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kanji_id")
    private int kanjiId;
    private String kanji;
    private String level;
    private String meaning;
    private String romaji;
    private int soundId;

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Kanji(String kanji, String meaning, String romaji, int soundId) {
        this.kanji = kanji;
        this.meaning = meaning;
        this.romaji = romaji;
        this.soundId = soundId;
    }

    public int getKanjiId() {
        return kanjiId;
    }

    public void setKanjiId(int kanjiId) {
        this.kanjiId = kanjiId;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }
}
