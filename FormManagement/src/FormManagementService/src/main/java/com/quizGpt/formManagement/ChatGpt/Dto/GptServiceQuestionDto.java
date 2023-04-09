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
    @JsonProperty("id")
    private int id;
    @JsonProperty("text")
    private String Text;
    @JsonProperty("sender")
    private String Sender;

}
