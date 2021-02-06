package com.kanjistudy.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kanjistudy.models.Kana;

import java.util.List;

@Dao
public interface KanaDao {

    @Query("SELECT * FROM Kana")
    List<Kana> getAllHiraganas();

    @Insert
    void insert(Kana kana);

    @Update
    void update(Kana kana);

    @Delete
    void delete(Kana kana);

    @Query("SELECT * FROM Kana where vowel = :vowel")
    List<Kana> getHiraganaByVowel(String vowel);

    @Query("SELECT * FROM Kana where consonant = :consonant")
    List<Kana> getHiraganaByConsonant(String consonant);

    @Query("SELECT hiragana FROM Kana ")
    String[] getHiraganaNames();

    @Query("SELECT katakana FROM Kana")
    String[] getKatakanaNames();
}
