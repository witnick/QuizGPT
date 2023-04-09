package com.quizGpt.formManagement.ChatGpt.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GptServiceResponseDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("number")
    private int Number;
    @JsonProperty("results")
    private List<GptQaDto> Results;
}
