package com.kanjistudy.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kanjistudy.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM Kanji")
    List<User> getAllUsers();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT name FROM User ")
    String[] getUserNames();

    @Query("SELECT * FROM User where user_id = :userId")
    User getUserById(int userId);


}
