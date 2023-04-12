package com.quizGpt.formManagement.ChatGpt.Service;

import com.quizGpt.formManagement.ChatGpt.Dto.GptServiceResponseDto;
import com.quizGpt.formManagement.ChatGpt.Exception.ConversationNotFoundException;

public interface IMessageConsumer {

    public void ReadMessageFromGptServer(GptServiceResponseDto gptResponse) throws ConversationNotFoundException;

}
