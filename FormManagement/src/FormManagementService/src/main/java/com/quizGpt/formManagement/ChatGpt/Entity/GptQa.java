package com.quizGpt.formManagement.ChatGpt.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "GptQestionAnswer")
public class GptQa {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String Answer;
    private String Question;
}
