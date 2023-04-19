package com.quizGpt.formManagement.Quiz.Exception;

public class QuestionNotFoundException  extends Exception {
    public QuestionNotFoundException(String errorMessage ) {
        super(errorMessage);
    }
}