package com.suraj.QuizApp.controller;

import com.suraj.QuizApp.model.Question;
import com.suraj.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allquestion")
    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return  new ResponseEntity<>(questionService.getAllQuestion(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return new ResponseEntity<>(questionService.getQuestionByCategory(category),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return new ResponseEntity<>(questionService.addQuestion(question),HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable int id,@RequestBody Question question){
        try {
            return new ResponseEntity<>(questionService.updateQuestion(id,question),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to Update!!",HttpStatus.BAD_REQUEST);
    }
}
