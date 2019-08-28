package com.happiest_mind.dictionary.services;

import com.happiest_mind.dictionary.model.Word;

import java.util.HashSet;
import java.util.List;

public interface DictionaryService {

    Word findById(int id);
    List<Word> findByWord(String word);
    Iterable<Word> findAll();
    boolean saveAll(HashSet<Word> word);
    Word deleteAll();
    void deleteAllByWord(String word);
}
