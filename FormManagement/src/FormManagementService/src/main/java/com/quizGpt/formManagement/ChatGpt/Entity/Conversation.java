package com.quizGpt.formManagement.ChatGpt.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "Conversation")
public class Conversation {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long QuizId;
    @CreatedDate
    private LocalDateTime CreatedAt;
    @LastModifiedDate
    private LocalDateTime  UpdatedAt;



    @OneToMany(targetEntity = GptServiceQuestion.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name="conversationId_fk", referencedColumnName = "Id")
    List<GptServiceQuestion> Questions;

    @OneToMany(targetEntity = GptServiceResponse.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name="conversationId_fk", referencedColumnName = "Id")
    List<GptServiceResponse> Responses;
}
