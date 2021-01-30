package com.kanjistudy.database;

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
    void insert(KanjiDb a);

    @Update
    void update(KanjiDb a);

    @Delete
    void delete(KanjiDb a);

    @Query("SELECT kanji FROM Kanji ")
    String[] getKanjiNames();


}
