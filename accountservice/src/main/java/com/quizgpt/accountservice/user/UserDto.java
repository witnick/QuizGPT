package com.quizgpt.accountservice.user;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
}
