package com.kanjistudy.database.repo;

import com.kanjistudy.database.dao.KanjiDao;
import com.kanjistudy.models.KanjiDb;

import java.util.List;

public class KanjiRepository {

    KanjiDao kanjiDao;

    public KanjiRepository(KanjiDao kanjiDao) {
        this.kanjiDao = kanjiDao;
    }


    public List<KanjiDb> getAllKanjis() {
        return kanjiDao.getAllKanjis();
    }

    public void insert(KanjiDb kanji) {
        kanjiDao.insert(kanji);
    }

    public void update(KanjiDb kanji) {
        kanjiDao.update(kanji);
    }

    public void delete(KanjiDb kanji) {
        kanjiDao.delete(kanji);
    }

    public String[] getKanjiNames() {
        return kanjiDao.getKanjiNames();
    }

    public List<KanjiDb> getKanjisByLevel(int level) {
        return kanjiDao.getKanjisByLevel(level);
    }

}