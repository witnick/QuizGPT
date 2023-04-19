package com.quizGpt.formManagement.Quiz.Dto;

import com.quizGpt.formManagement.Quiz.Common.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionDto {
    private Long questionId;
    private String text;
    private QuestionType type;
    private boolean isRequired;
    private int order;

    private String createdAt;
    private String updatedAt;

    private List<OptionDto> options;
    private List<AnswerDto> answers;
}
