package com.quizGpt.formManagement.ChatGpt.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue
    private int id;
    @JsonProperty("answer")
    private String Answer;
    @JsonProperty("question")
    private String Question;
}
