package com.happiest_mind.dictionary.dao;

import com.happiest_mind.dictionary.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface DictionaryDAO extends JpaRepository<Word,Long> {

    @Query("from Word where word=?1")
    List<Word> findByWord(String Word);
    @Transactional
    void deleteAllByWord(String word);
}
