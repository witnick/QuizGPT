package com.quizGpt.formManagement.ChatGpt.Service;

import com.quizGpt.formManagement.ChatGpt.Dto.GptServiceResponseDto;

public interface IMessageConsumer {

    public Object ReadMessageFromGptServer(GptServiceResponseDto gptResponse);

}
