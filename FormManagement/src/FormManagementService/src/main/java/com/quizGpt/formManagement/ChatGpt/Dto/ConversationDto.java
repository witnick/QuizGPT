package com.quizGpt.formManagement.ChatGpt.Dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConversationDto {
    private Long Id;
    private Long Number;
    private LocalDateTime CreationDate;
    private LocalDateTime Conversation;

    List<GptServiceQuestionDto> Question;
    List<GptServiceResponseDto> Responses;
}