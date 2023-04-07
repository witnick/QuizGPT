package com.quizGpt.formManagement.Quiz.Controller;

import com.quizGpt.formManagement.Quiz.Dto.QuestionDto;
import com.quizGpt.formManagement.Quiz.Dto.QuizDto;
import com.quizGpt.formManagement.Quiz.Entity.Question;
import com.quizGpt.formManagement.Quiz.Entity.Quiz;
import com.quizGpt.formManagement.Quiz.Exception.QuestionNotFoundException;
import com.quizGpt.formManagement.Quiz.Exception.QuizNotFoundException;
import com.quizGpt.formManagement.Quiz.Repository.QuizRepository;
import com.quizGpt.formManagement.Quiz.Services.IQuizService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( "api/v1")
public class QuizController {
    @Autowired
    private ModelMapper modelMapper;

    private IQuizService quizService;

    QuizController(IQuizService quizService) {
        this.quizService = quizService;
    }

    ///Quiz
    @GetMapping("/quizzes")
    public List<QuizDto> GetAllQuizzes(){
        List<QuizDto> quizDtos = new ArrayList<QuizDto>();
        List<Quiz> quizzes = this.quizService.GetAllQuizzes();

        for (Quiz quiz : quizzes) {
            QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
            quizDtos.add(quizDto);
        }

        return quizDtos;
    }

    @GetMapping("/quiz/{id}")
    public QuizDto GetQuiz(@PathVariable Long id) throws QuizNotFoundException {
        Quiz quiz =  quizService.GetQuizById(id);
        var quizDto = modelMapper.map(quiz, QuizDto.class);
        return quizDto;
    }

    @PostMapping("/quiz")
    public QuizDto CreateQuiz(@RequestBody QuizDto newQuiz){
        var quiz = modelMapper.map(newQuiz, Quiz.class);
        quiz.setCreatedAt(LocalDateTime.now());
        quiz.setUpdatedAt(LocalDateTime.now());
        newQuiz = modelMapper.map(quizService.SaveQuiz(quiz), QuizDto.class);
        return newQuiz;
    }

    @PutMapping("/quiz")
    public void UpdateQuiz(@RequestBody @NotNull QuizDto quizRequest) throws QuizNotFoundException {
        Quiz quizEntity = quizService.GetQuizById(quizRequest.getId());
        var quizToUpdate = modelMapper.map(quizRequest, Quiz.class);
        quizService.SaveQuiz(quizToUpdate);
    }

    @DeleteMapping("/quiz/{id}")
    public void DeleteQuiz(@PathVariable Long id) throws QuizNotFoundException {
        quizService.DeleteQuiz(id);
    }

    ///Questions
    @GetMapping("/questions")
    public List<QuestionDto> GetAllQuestions(){
        List<QuestionDto> questionDtos = new ArrayList<>();
        List<Question> questions = this.quizService.GetAllQuestions();

        for (Question question : questions) {
            QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
            questionDtos.add(questionDto);
        }

        return questionDtos;
    }

    @GetMapping("/questions/{id}")
    public QuestionDto GetQuestion(@PathVariable Long id) throws QuestionNotFoundException {
        Question Question =  quizService.GetQuestionById(id);
        var QuestionDto = modelMapper.map(Question, QuestionDto.class);
        return QuestionDto;
    }

    @PostMapping("/questions")
    public QuestionDto createQuestion(@RequestBody QuestionDto newQuestion) {
        var Question = modelMapper.map(newQuestion, Question.class);
        newQuestion = modelMapper.map(quizService.SaveQuestion(Question), QuestionDto.class);
        return newQuestion;
    }

    @PutMapping("/questions")
    public void updateQuestion(@RequestBody @NotNull QuestionDto QuestionRequest) throws QuestionNotFoundException {
        Question QuestionEntity = quizService.GetQuestionById(QuestionRequest.getQuestionId());
        var QuestionToUpdate = modelMapper.map(QuestionRequest, Question.class);
        quizService.SaveQuestion(QuestionToUpdate);
    }

    @DeleteMapping("/questions/{id}")
    public void DeleteQuestion(@PathVariable Long id) throws QuestionNotFoundException {
        quizService.DeleteQuestion(id);
    }

    ///Quiz Attempt
}

