package com.quizGpt.formManagement.Quiz.Entity;

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
@Table(name = "QuizAttempt")
public class QuizAttempt {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long quizAttemptId;
    private Long quizId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(targetEntity = UserAnswer.class, cascade = CascadeType.ALL)
    @JoinColumn( name = "quizAttemptId_fk", referencedColumnName = "quizAttemptId")
    private List<UserAnswer> userAnswerList;

}
