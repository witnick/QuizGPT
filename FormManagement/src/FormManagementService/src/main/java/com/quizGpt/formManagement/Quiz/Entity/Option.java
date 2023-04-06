package com.quizGpt.formManagement.Quiz.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "Option")
public class Option {
    @Id
    @GeneratedValue
    private Long optionId;
    private Long questionId;
    private String text;
    private Integer value;
    private int ordering;
    private LocalDateTime  createdAt;
    private LocalDateTime updatedAt;
}