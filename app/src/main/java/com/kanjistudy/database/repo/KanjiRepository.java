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

    public void update(Kanji kanji) {
        kanjiDao.update(kanji);
    }

    public void delete(Kanji kanji) {
        kanjiDao.delete(kanji);
    }

    public String[] getKanjiNames() {
        return kanjiDao.getKanjiNames();
    }

    public List<Kanji> getKanjisByLevel(int level) {
        return kanjiDao.getKanjisByLevel(level);
    }

}
