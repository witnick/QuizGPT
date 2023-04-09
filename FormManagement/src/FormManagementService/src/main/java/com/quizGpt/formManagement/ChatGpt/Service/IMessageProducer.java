package com.quizGpt.formManagement.ChatGpt.Service;

import com.quizGpt.formManagement.ChatGpt.Dto.GptServiceQuestionDto;

public interface IMessageProducer {
    void SendMessageToGptServer(String message);

    void SendMessageToAuthServer(String message);

    void SendMessageToGptServer(GptServiceQuestionDto message);
}
