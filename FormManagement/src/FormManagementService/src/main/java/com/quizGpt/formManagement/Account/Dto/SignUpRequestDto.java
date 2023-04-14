package com.quizGpt.formManagement.Account.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequestDto {
    @JsonProperty("username")
    private String Username;
    @JsonProperty("password")
    private String Password;
    @JsonProperty("email")
    private String Email;
}
