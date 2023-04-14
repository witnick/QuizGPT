package com.quizGpt.formManagement.Account.Controller;

import com.quizGpt.formManagement.Account.Dto.LoginRequestDto;
import com.quizGpt.formManagement.Account.Dto.SignUpRequestDto;
import com.quizGpt.formManagement.Account.Service.SecurityMqService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class AccountController {

    private SecurityMqService securityMqService;

    public AccountController(SecurityMqService securityMqService) {
        this.securityMqService = securityMqService;
    }

    @PostMapping("/account/login")
    private void Login(@RequestBody LoginRequestDto request){
        securityMqService.SendLoginMessageToMqServer(request);
    }

    @PostMapping("/account/signup")
    private void Login(@RequestBody SignUpRequestDto request){
        securityMqService.SendSignUpMessageToMqServer(request);
    }

}
