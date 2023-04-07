package com.quizGpt.formManagement.Quiz.Services;

import com.quizGpt.formManagement.Quiz.Entity.Quiz;
import com.quizGpt.formManagement.Quiz.Exception.QuizNotFoundException;

import java.util.List;

public interface IQuizService {
    List<Quiz> getAllQuizzes();
    Quiz getQuizById(Long id) throws QuizNotFoundException;
    Quiz saveQuiz(Quiz quiz);
    void deleteQuiz(Long id);
}
