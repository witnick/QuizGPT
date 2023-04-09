package com.quizGpt.formManagement.ChatGpt.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quizGpt.formManagement.Quiz.Entity.Answer;
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
@Table(name = "GptServiceResponse")
public class GptServiceResponse {
    @Id
    private int id;
    private int quizId;
    private int Number;
    @OneToMany(targetEntity = GptQa.class, cascade = CascadeType.ALL)
    @JoinColumn( name="id_fk", referencedColumnName = "id")
    private List<GptQa> Results;
}
