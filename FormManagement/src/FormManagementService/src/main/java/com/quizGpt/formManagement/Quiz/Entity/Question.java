package com.quizGpt.formManagement.Quiz.Entity;

import com.quizGpt.formManagement.Quiz.Common.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue
    private Long questionId;
    private String text;
    private QuestionType type;
    private boolean isRequired;
    private int ordering;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(targetEntity = Option.class, cascade = CascadeType.ALL)
    @JoinColumn( name="questionId_fk", referencedColumnName = "questionId")
    private List<Option> options;
    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL)
    @JoinColumn( name="questionId_fk", referencedColumnName = "questionId")
    private List<Answer> answers;
}