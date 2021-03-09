package com.kanjistudy.database.repo;

import com.kanjistudy.database.dao.UserDao;
import com.kanjistudy.models.User;

import java.util.List;

public class UserRepository {

    UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }


    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void insert(User user) {
        userDao.insert(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public String[] getUserNames() {
        return userDao.getUserNames();
    }

    public User getUserById(int level) {
        return userDao.getUserById(level);
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
