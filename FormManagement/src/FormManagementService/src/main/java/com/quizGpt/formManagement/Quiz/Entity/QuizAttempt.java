package com.quizGpt.formManagement.Quiz.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "QuizAttempt")
public class QuizAttempt {
    @Id
    @GeneratedValue
    public Long quizAttemptId;
    public Long quizId;

    @OneToMany(targetEntity = UserAnswer.class, cascade = CascadeType.ALL)
    @JoinColumn( name = "quizAttemptId_fk", referencedColumnName = "quizAttemptId")
    public List<UserAnswer> userAnswerList;

}
