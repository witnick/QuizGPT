package com.quizGpt.formManagement.Quiz.Controller;

import com.quizGpt.formManagement.Quiz.Dto.QuizDto;
import com.quizGpt.formManagement.Quiz.Entity.Quiz;
import com.quizGpt.formManagement.Quiz.Exception.QuizNotFoundException;
import com.quizGpt.formManagement.Quiz.Repository.QuizRepository;
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

    @Autowired
    private final QuizRepository repository;

    QuizController(QuizRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/quizzes")
    public List<Quiz> all(){
        List<Quiz> quizzes = this.repository.findAll();
        return quizzes;
    }

    @GetMapping("/quiz/{id}")
    public QuizDto GetQuiz(@PathVariable Long id) throws QuizNotFoundException {
        Quiz quiz =  repository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found"));
        var quizDto = modelMapper.map(quiz, QuizDto.class);
        return quizDto;
    }

    @PostMapping("/quiz")
    public Quiz createQuiz(@RequestBody QuizDto newQuiz){
        var quiz = modelMapper.map(newQuiz, Quiz.class);
        return repository.save(quiz);
    }

//    @PutMapping("/quiz")
//    public void updateQuiz(@RequestBody QuizDto quizRequest){
//        Quiz quizEntity = repository.findById(quizRequest.getId());
//        modelMapper.map(quizRequest, Quiz.class);
//    }

    @DeleteMapping("/quiz/{id}")
    public void DeleteQuiz(@PathVariable Long id){
        repository.deleteById(id);
    }
}

