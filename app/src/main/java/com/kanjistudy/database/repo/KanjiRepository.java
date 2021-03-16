package com.kanjistudy.database.repo;

import com.kanjistudy.database.dao.KanjiDao;
import com.kanjistudy.models.Kanji;

import java.util.List;

public class KanjiRepository {

    KanjiDao kanjiDao;

    public KanjiRepository(KanjiDao kanjiDao) {
        this.kanjiDao = kanjiDao;
    }


    public List<Kanji> getAllKanjis() {
        return kanjiDao.getAllKanjis();
    }

    public void insert(Kanji kanji) {
        kanjiDao.insert(kanji);
    }

    public List<Kanji> getKanjisByLevel(int level) {
        return kanjiDao.getKanjisByLevel(level);
    }

}
