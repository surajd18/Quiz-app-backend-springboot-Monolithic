package com.suraj.QuizApp.service;

import com.suraj.QuizApp.model.Question;
import com.suraj.QuizApp.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;
    public List<Question> getAllQuestion() {
        return questionRepo.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionRepo.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionRepo.save(question);
        return "Success";
    }

    public String updateQuestion(int id, Question question) {
        return questionRepo.findById(id).map(oldQuestion ->{
            oldQuestion.setQuestionTitle(question.getQuestionTitle());
            oldQuestion.setCategory(question.getCategory());
            oldQuestion.setDifficultlevel(question.getDifficultlevel());
            oldQuestion.setOption1(question.getOption1());
            oldQuestion.setOption2(question.getOption2());
            oldQuestion.setOption3(question.getOption3());
            oldQuestion.setRightAnswer(question.getRightAnswer());

            questionRepo.save(oldQuestion);

            return "Question Updated Successfully!!";
        }).orElse("Qoestion Not Found!!");
    }
}
