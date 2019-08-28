package com.happiest_mind.dictionary.services;

import com.happiest_mind.dictionary.dao.DictionaryDAO;
import com.happiest_mind.dictionary.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryDAO dictionaryDAO;

    @Override
    public Word findById(int id) {
        return null;
    }

    @Override
    public List<Word> findByWord(String word) {
        return dictionaryDAO.findByWord(word);
    }

    @Override
    public Iterable<Word> findAll() {
        return dictionaryDAO.findAll();
    }

    @Override
    public boolean saveAll(HashSet<Word> wordList) {
        try {
            dictionaryDAO.saveAll(wordList);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Word deleteAll() {
        return null;
    }

    @Override
    public void deleteAllByWord(String word) {
         dictionaryDAO.deleteAllByWord(word);
    }
}
