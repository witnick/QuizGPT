package com.quizGpt.formManagement.Quiz.Services;


import com.quizGpt.formManagement.Quiz.Entity.Question;
import com.quizGpt.formManagement.Quiz.Entity.Quiz;
import com.quizGpt.formManagement.Quiz.Entity.QuizAttempt;
import com.quizGpt.formManagement.Quiz.Exception.QuestionNotFoundException;
import com.quizGpt.formManagement.Quiz.Exception.QuizAttemptNotFoundException;
import com.quizGpt.formManagement.Quiz.Exception.QuizNotFoundException;
import com.quizGpt.formManagement.Quiz.Repository.QuestionRepository;
import com.quizGpt.formManagement.Quiz.Repository.QuizAttemptRepository;
import com.quizGpt.formManagement.Quiz.Repository.QuizRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService implements IQuizService {

//    @Autowired
    private final QuizRepository quizRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final QuestionRepository questionRepository;

//    @Autowired
    private final ModelMapper modelMapper;

    public QuizService(QuizRepository quizRepository, QuizAttemptRepository quizAttemptRepository, QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.quizRepository = quizRepository;
        this.quizAttemptRepository = quizAttemptRepository;
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Quiz> GetAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz GetQuizById(Long id) throws QuizNotFoundException {
        return quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException("Quiz not found with id: " + id));
    }

    @Override
    public Quiz SaveQuiz(Quiz quiz) {
        return quizRepository.saveAndFlush(quiz);
    }

    @Override
    public void DeleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public List<Question> GetAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question GetQuestionById(Long id) throws QuestionNotFoundException {
        return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + id));
    }

    @Override
    public Question SaveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void DeleteQuestion(Long id){
        questionRepository.deleteById(id);
    }

    @Override
    public List<QuizAttempt> GetAllQuizAttempts() {
        return quizAttemptRepository.findAll();
    }

    @Override
    public QuizAttempt GetQuizAttemptById(Long id) throws QuizAttemptNotFoundException {
        return quizAttemptRepository.findById(id).orElseThrow(() -> new QuizAttemptNotFoundException("Quiz attempt not found with id: " + id));
    }

    @Override
    public QuizAttempt SaveQuizAttempt(QuizAttempt attempt) {
        return quizAttemptRepository.save(attempt);
    }

    @Override
    public void DeleteQuizAttempt(Long id) {
        quizAttemptRepository.deleteById(id);
    }
}