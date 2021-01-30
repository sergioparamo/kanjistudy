package com.kanjistudy.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Kanji")
public class KanjiDb {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "question_id")
    private int questionId;
    private String kanji;
    private String nivel;

    public KanjiDb(String kanji) {
        this.kanji = kanji;

    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
