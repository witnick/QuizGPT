package com.quizGpt.formManagement.Account.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponseDto {
    private Body body;
    private Headers headers;
    private String statusCode;
    private int statusCodeValue;

    @Data
    @NoArgsConstructor
    public class Body {
        private String accessToken;
        private String email;
        private int id;
        private List<String> roles;
        private String tokenType;
        private String username;
    }

    @Data
    @NoArgsConstructor
    public class Headers {
        // Add fields for headers if necessary
    }
}