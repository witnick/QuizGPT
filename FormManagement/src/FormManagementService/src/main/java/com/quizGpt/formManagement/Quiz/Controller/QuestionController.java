package com.quizGpt.formManagement.Quiz.Controller;

import com.quizGpt.formManagement.Quiz.Repository.QuizRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "api/v1")
public class QuestionController {
    @Autowired
    private ModelMapper modelMapper;

}
