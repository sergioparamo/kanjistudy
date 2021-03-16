package com.kanjistudy.database.repo;

import com.kanjistudy.database.dao.UserDao;
import com.kanjistudy.models.User;

import java.util.List;

public class UserRepository {

    UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public void insert(User user) {
        userDao.insert(user);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public String getPasswordByUsername(String username) {
        return userDao.getPasswordByUsername(username);
    }

    public User getUserByMail(String mail) {
        return userDao.getUserByMail(mail);
    }


}
