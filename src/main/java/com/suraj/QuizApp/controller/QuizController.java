package com.suraj.QuizApp.controller;

import com.suraj.QuizApp.model.Quiz;
import com.suraj.QuizApp.model.QuizWrapper;
import com.suraj.QuizApp.model.Response;
import com.suraj.QuizApp.service.QuestionService;
import com.suraj.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return new ResponseEntity<>(quizService.createQuiz(category,numQ,title), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuizWrapper>> getQuizQuestion(@PathVariable Integer id){
        return new ResponseEntity<>(quizService.getQuizQuestion(id),HttpStatus.OK);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return new ResponseEntity<>(quizService.calculateresult(id,responses),HttpStatus.OK);
    }
}
