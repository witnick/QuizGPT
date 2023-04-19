package com.quizGpt.formManagement.ChatGpt.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GptServiceResponseDto {
    @JsonProperty("conversationId")
    private Long ConversationId;
    @JsonProperty("number")
    private Long Number;
    @JsonProperty("results")
    private List<GptQaDto> Results;
}
