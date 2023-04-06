package com.quizGpt.formManagement.Quiz.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OptionDto {
    private Long optionId;
    private Long questionId;
    private String text;
    private Integer value;
    private int order;

    private String  createdAt;
    private String updatedAt;
}