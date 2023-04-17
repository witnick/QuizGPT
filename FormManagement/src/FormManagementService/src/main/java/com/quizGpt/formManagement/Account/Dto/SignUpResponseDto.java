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
    //private Headers headers;
    private BodyDto bodyDto;
    private String statusCode;
    private int statusCodeValue;
}
