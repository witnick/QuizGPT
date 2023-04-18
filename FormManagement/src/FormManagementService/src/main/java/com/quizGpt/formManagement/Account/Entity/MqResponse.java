package com.quizGpt.formManagement.Account.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MqResponse")
public class MqResponse {
    @Id
    private String correlationId;
    @NotNull
    @Column(length = Integer.MAX_VALUE)
    private String response;

}
