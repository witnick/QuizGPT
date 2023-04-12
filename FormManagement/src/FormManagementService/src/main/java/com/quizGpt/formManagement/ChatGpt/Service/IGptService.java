package com.quizGpt.formManagement.ChatGpt.Service;

import com.quizGpt.formManagement.ChatGpt.Dto.ConversationDto;
import com.quizGpt.formManagement.ChatGpt.Entity.Conversation;
import com.quizGpt.formManagement.ChatGpt.Exception.ConversationNotFoundException;
import com.quizGpt.formManagement.Quiz.Exception.QuizNotFoundException;

public interface IGptService {
    public ConversationDto GetGptServiceConversation(Long conversationId ) throws ConversationNotFoundException;

    Conversation CreateOrReadConversationByQuizId(Long quizId) throws QuizNotFoundException;

    Conversation SaveConversation(Conversation conversation);

    void DeleteConversation(Long id);
}
