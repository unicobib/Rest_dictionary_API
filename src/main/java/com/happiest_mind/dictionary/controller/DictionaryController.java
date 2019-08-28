package com.happiest_mind.dictionary.controller;

import com.happiest_mind.dictionary.model.Word;
import com.happiest_mind.dictionary.services.DictionaryServiceImpl;
import org.hibernate.annotations.common.reflection.XMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class DictionaryController {

    @Autowired
    private DictionaryServiceImpl dictionaryService;

    @GetMapping(value = "/")
    public String getUploadForm(){
        return "UploadFile";
    }

    @PostMapping
    public String uploadTextFile(@RequestParam("file") MultipartFile textFile, Model model) throws IOException {
        System.out.println("UploadFile............");
        HashSet<Word> wordList = new HashSet<>();
        String file = new String(textFile.getBytes());
        file = file.replaceAll("[.,()\\[\\]{}]"," ").replaceAll("\\s+"," ");
        System.out.println(file);
        String[] word = file.split(" ");
        Arrays.stream(word).parallel().filter(w->w.matches("\\w+")).map(String::toLowerCase).forEach(val->{wordList.add(new Word(val));});
        boolean message = dictionaryService.saveAll(wordList);
        model.addAttribute("messageSuccess",message);
        model.addAttribute("messageFail",message?false:true);
        return "searchPage";
    }

    @GetMapping("/words")
    public String getAllWords(Model model){
        System.out.print("get All words..........");
        //Iterable<Word> wordIterable = dictionaryService.findAll();
        //List<String> wordList = new ArrayList<>();
        //wordIterable.forEach(wordVal->{wordList.add(wordVal.getWord());});
        model.addAttribute("listOfWord",dictionaryService.findAll());
        return "searchPage";

    }

    @GetMapping("/words/{wordName}")
    public String getWordByName(@PathVariable("wordName") String wordName){
        System.out.println("get word by name:  "+wordName);
        List<Word> word = dictionaryService.findByWord(wordName);
        return word.isEmpty()?"word not exist":word.get(0).getWord();
    }

    @DeleteMapping("/words/{wordName}")
    public void deleteWord(@PathVariable("wordName") String wordName){
        System.out.println("delete word:   "+wordName);
        dictionaryService.deleteAllByWord(wordName);

    }

}
