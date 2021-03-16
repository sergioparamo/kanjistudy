package com.kanjistudy.database.repo;

import com.kanjistudy.database.dao.KanaDao;
import com.kanjistudy.models.Kana;

import java.util.List;

public class KanaRepository {

    KanaDao kanaDao;

    public KanaRepository(KanaDao kanaDao) {
        this.kanaDao = kanaDao;
    }

    public List<Kana> getAllKanas() {
        return kanaDao.getAllHiraganas();
    }

    public void insert(Kana kana) {
        kanaDao.insert(kana);
    }

}
