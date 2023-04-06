package com.quizGpt.formManagement.Quiz.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "Answer")
public class Answer {

    @Id
    @GeneratedValue
    private Long answerId;
    private String text;
    private Integer value;
}
