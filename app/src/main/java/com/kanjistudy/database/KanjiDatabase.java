package com.kanjistudy.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;


public abstract class KanjiDatabase extends RoomDatabase  {

    public static KanjiDatabase INSTANCE;

    public abstract KanjiDao kanjiDao();

    public static KanjiDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, KanjiDatabase.class, "Room.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
