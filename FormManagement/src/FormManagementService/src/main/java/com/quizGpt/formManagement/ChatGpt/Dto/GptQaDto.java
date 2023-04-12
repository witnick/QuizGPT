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
public class GptQaDto {
    @JsonProperty("answer")
    private String Answer;
    @JsonProperty("question")
    private String Question;
}
