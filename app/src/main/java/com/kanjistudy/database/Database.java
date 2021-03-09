package com.kanjistudy.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kanjistudy.database.dao.KanaDao;
import com.kanjistudy.database.dao.KanjiDao;
import com.kanjistudy.database.dao.UserDao;
import com.kanjistudy.models.Kana;
import com.kanjistudy.models.Kanji;
import com.kanjistudy.models.User;

@androidx.room.Database(entities = {Kanji.class, Kana.class, User.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public static Database INSTANCE;

    public abstract KanjiDao kanjiDao();

    public abstract KanaDao kanaDao();

    public abstract UserDao userDao();


    public static Database getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, Database.class, "Room.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
