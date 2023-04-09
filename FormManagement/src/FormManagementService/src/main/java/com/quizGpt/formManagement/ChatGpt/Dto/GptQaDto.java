package com.quizGpt.formManagement.ChatGpt.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GptQaDto {
    @JsonProperty("answer")
    private String Answer;
    @JsonProperty("question")
    private String Question;
}
