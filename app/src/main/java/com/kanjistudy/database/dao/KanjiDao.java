package com.kanjistudy.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kanjistudy.models.Kanji;

import java.util.List;

@Dao
public interface KanjiDao {

    @Query("SELECT * FROM Kanji")
    List<Kanji> getAllKanjis();

    @Insert
    void insert(Kanji kanji);

    @Update
    void update(Kanji kanji);

    @Delete
    void delete(Kanji kanji);

    @Query("SELECT kanji FROM Kanji ")
    String[] getKanjiNames();

    @Query("SELECT * FROM Kanji where level = :level")
    List<Kanji> getKanjisByLevel(int level);

}
