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

    public void update(Kana kana) {
        kanaDao.update(kana);
    }

    public void delete(Kana kana) {
        kanaDao.delete(kana);
    }

    public String[] getHiraganaNames() {
        return kanaDao.getHiraganaNames();
    }

    public String[] getKatakanaNames() {
        return kanaDao.getKatakanaNames();
    }

    public List<Kana> getHiraganaByVowel(String vowel) {
        return kanaDao.getHiraganaByVowel(vowel);
    }

    public List<Kana> getHiraganaByConsonant(String consonant) {
        return kanaDao.getHiraganaByConsonant(consonant);
    }


}
