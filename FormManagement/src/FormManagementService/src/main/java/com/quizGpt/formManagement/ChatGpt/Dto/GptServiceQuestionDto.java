package com.quizGpt.formManagement.ChatGpt.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GptServiceQuestionDto {
    //quizId
    private Long Id;
    @JsonProperty("conversationId")
    private Long ConvoId;
    @JsonProperty("quizId")
    private Long QuizId;
    @JsonProperty("number")
    private int Number;
    @JsonProperty("text")
    private String Text;
    @JsonProperty("sender")
    private String Sender;

}
