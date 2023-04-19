package com.quizGpt.formManagement.ChatGpt.Exception;

public class ConversationNotFoundException extends Exception {
    public ConversationNotFoundException(String errorMessage ) {
        super(errorMessage);
    }
}
