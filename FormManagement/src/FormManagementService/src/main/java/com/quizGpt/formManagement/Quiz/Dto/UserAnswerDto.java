package com.quizGpt.formManagement.Quiz.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAnswerDto {

    public Long userAnswerId;
    public int userId;
    public int questionId;
    public String text;
    public Integer value;
}
