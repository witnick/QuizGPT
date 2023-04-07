package com.quizGpt.formManagement.Quiz.Controller;

import com.quizGpt.formManagement.Quiz.Dto.QuizDto;
import com.quizGpt.formManagement.Quiz.Entity.Quiz;
import com.quizGpt.formManagement.Quiz.Exception.QuizNotFoundException;
import com.quizGpt.formManagement.Quiz.Repository.QuizRepository;
import com.quizGpt.formManagement.Quiz.Services.IQuizService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/quizzes")
    public List<Quiz> all(){
        List<Quiz> quizzes = this.quizService.getAllQuizzes();

        return quizzes;
    }

    @GetMapping("/quiz/{id}")
    public QuizDto GetQuiz(@PathVariable Long id) throws QuizNotFoundException {
        Quiz quiz =  quizService.getQuizById(id);
        var quizDto = modelMapper.map(quiz, QuizDto.class);
        return quizDto;
    }

    @PostMapping("/quiz")
    public QuizDto createQuiz(@RequestBody QuizDto newQuiz){
        var quiz = modelMapper.map(newQuiz, Quiz.class);
        quiz.setCreatedAt(LocalDateTime.now());
        quiz.setUpdatedAt(LocalDateTime.now());
        newQuiz = modelMapper.map(quizService.saveQuiz(quiz), QuizDto.class);
        return newQuiz;
    }

    @PutMapping("/quiz")
    public void updateQuiz(@RequestBody @NotNull QuizDto quizRequest) throws QuizNotFoundException {
        Quiz quizEntity = quizService.getQuizById(quizRequest.getId());
        var quizToUpdate = modelMapper.map(quizRequest, Quiz.class);
        quizService.saveQuiz(quizToUpdate);
    }

    @DeleteMapping("/quiz/{id}")
    public void DeleteQuiz(@PathVariable Long id){
        quizService.deleteQuiz(id);
    }
}

