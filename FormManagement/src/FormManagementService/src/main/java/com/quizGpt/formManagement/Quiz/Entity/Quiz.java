package com.quizGpt.formManagement.Quiz.Entity;

import com.quizGpt.formManagement.ChatGpt.Entity.Conversation;
import com.quizGpt.formManagement.Quiz.Common.QuizStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "Quiz")
public class Quiz {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long UserId;
    private String Name;
    private String Description;
    private QuizStatus Status;
    @CreatedDate
    private LocalDateTime CreatedAt;
    @LastModifiedDate
    private LocalDateTime  UpdatedAt;

    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL)
    @JoinColumn( name="quizId_fk", referencedColumnName = "Id")
    private List<Question> Questions;

    @OneToOne(targetEntity = Conversation.class, cascade = CascadeType.ALL)
    @JoinColumn( name="quizId_fk", referencedColumnName = "Id")
    private Conversation GptConversation;

}