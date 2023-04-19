package com.quizGpt.formManagement.Quiz.Dto;


import com.quizGpt.formManagement.ChatGpt.Dto.GptServiceQuestionDto;
import com.quizGpt.formManagement.ChatGpt.Entity.Conversation;
import com.quizGpt.formManagement.Quiz.Common.QuizStatus;
import com.quizGpt.formManagement.Quiz.Entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizDto {
    private Long Id;
    private String Username;
    private String Name;
    private String Description;
    private QuizStatus Status;

    private String CreatedAt;
    private String  UpdatedAt;

    private List<QuestionDto> Questions;
    private Conversation GptConversation;

    private GptServiceQuestionDto GptRequest;

}
