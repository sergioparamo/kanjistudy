package com.kanjistudy.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kanjistudy.models.KanjiDb;

import java.util.List;

@Dao
public interface KanjiDao {

    @Query("SELECT * FROM Kanji")
    List<KanjiDb> getAllKanjis();

    @Insert
    void insert(KanjiDb kanji);

    @Update
    void update(KanjiDb kanji);

    @Delete
    void delete(KanjiDb kanji);

    @Query("SELECT kanji FROM Kanji ")
    String[] getKanjiNames();

    @Query("SELECT * FROM Kanji where level = :level")
    List<KanjiDb> getKanjisByLevel(int level);

}
