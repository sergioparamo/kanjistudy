package com.kanjistudy.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Kanji")
public class KanjiDb {
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

    public KanjiDb(String kanji) {
        this.kanji = kanji;

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
}
