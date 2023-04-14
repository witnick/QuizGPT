package com.quizGpt.formManagement.Account.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpResponseDto {
    private Headers headers;
    private Body body;
    private String statusCode;
    private int statusCodeValue;

    @Data
    @NoArgsConstructor
    public class Headers {
        // Add fields for headers if necessary
    }

    @Data
    @NoArgsConstructor
    public class Body {
        private int id;
        private String username;
        private String password;
        private String email;
    }
}
