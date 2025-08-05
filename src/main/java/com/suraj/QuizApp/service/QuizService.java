package com.suraj.QuizApp.service;

import com.suraj.QuizApp.model.Question;
import com.suraj.QuizApp.model.Quiz;
import com.suraj.QuizApp.model.QuizWrapper;
import com.suraj.QuizApp.model.Response;
import com.suraj.QuizApp.repository.QuestionRepo;
import com.suraj.QuizApp.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionRepo questionRepo;
    public String createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();

        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepo.save(quiz);
        return "Successfully Created Quiz!!";
    }

    public List<QuizWrapper> getQuizQuestion(Integer id) {

        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuizWrapper> questionForUser = new ArrayList<>();

        for(Question q: questionsFromDB){
            QuizWrapper qw = new QuizWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }
        return questionForUser;
    }

    public Integer calculateresult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questions = quiz.get().getQuestions();

        int right =0;
        int i =0;
        for(Response r: responses){
            if(r.getResponse().equals(questions.get(1).getRightAnswer())){
                right++;
            }
            i++;
        }
        return right;
    }
}
