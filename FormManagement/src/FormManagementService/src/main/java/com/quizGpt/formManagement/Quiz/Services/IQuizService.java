package com.quizGpt.formManagement.Quiz.Services;

import com.quizGpt.formManagement.Quiz.Entity.Question;
import com.quizGpt.formManagement.Quiz.Entity.Quiz;
import com.quizGpt.formManagement.Quiz.Entity.QuizAttempt;
import com.quizGpt.formManagement.Quiz.Exception.QuestionNotFoundException;
import com.quizGpt.formManagement.Quiz.Exception.QuizAttemptNotFoundException;
import com.quizGpt.formManagement.Quiz.Exception.QuizNotFoundException;

import java.util.List;

public interface IQuizService {
    List<Quiz> GetAllQuizzes();
    Quiz GetQuizById(Long id) throws QuizNotFoundException;
    Quiz SaveQuiz(Quiz quiz);
    void DeleteQuiz(Long id) throws QuizNotFoundException;
    List<Question> GetAllQuestions();
    Question GetQuestionById(Long id) throws QuestionNotFoundException;
    Question SaveQuestion(Question question);
    void DeleteQuestion(Long id);
    List<QuizAttempt> GetAllQuizAttempts();
    QuizAttempt GetQuizAttemptById(Long id) throws QuizAttemptNotFoundException;
    QuizAttempt SaveQuizAttempt(QuizAttempt attempt);
    void DeleteQuizAttempt(Long id);


}