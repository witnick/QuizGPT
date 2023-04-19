package com.quizGpt.formManagement.Quiz.Dto;

import com.quizGpt.formManagement.Quiz.Entity.UserAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizAttemptDto {
    public Long quizAttemptId;
    public int quizId;
    public String username;
    public List<UserAnswer> userAnswerList;

}