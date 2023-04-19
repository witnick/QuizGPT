package com.quizGpt.formManagement.Account.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BodyDto {
    private String accessToken;
    private String email;
    private int id;
    private List<String> roles;
    private String tokenType;
    private String username;
    private String password;
}
