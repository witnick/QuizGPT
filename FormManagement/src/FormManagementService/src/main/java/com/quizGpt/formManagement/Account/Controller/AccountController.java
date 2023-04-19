package com.quizGpt.formManagement.Account.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizGpt.formManagement.Account.Dto.LoginRequestDto;
import com.quizGpt.formManagement.Account.Dto.LoginResponseDto;
import com.quizGpt.formManagement.Account.Dto.SignUpRequestDto;
import com.quizGpt.formManagement.Account.Dto.SignUpResponseDto;
import com.quizGpt.formManagement.Account.Service.AccountService;
import com.quizGpt.formManagement.Account.Service.SecurityMqService;
import com.quizGpt.formManagement.Common.Controller.MqController;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/v1/")
public class AccountController extends MqController {

    private SecurityMqService securityMqService;

    private HttpSession session;
    private ObjectMapper objectMapper;

    public AccountController(SecurityMqService securityMqService, AccountService accountService, HttpSession session, ObjectMapper objectMapper ) {
        super(accountService);
        this.securityMqService = securityMqService;
        this.session = session;
        this.objectMapper = objectMapper;

    }

    @PostMapping("/account/login")
    public @NotNull ResponseEntity Login(@RequestBody LoginRequestDto request) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        //send request
        String correlationId = securityMqService.SendLoginMessageToMqServer(request);
        //wait for response to be saved to db
        var response = GetResponseOrWait(request.getUsername());
        String res = response.get();

        // save user to Db with expiration
        LoginResponseDto loginDto = null;
        if (res != null) {

            loginDto = objectMapper.readValue(res, LoginResponseDto.class);

            session.setAttribute("username", loginDto.getBody().getUsername());
            session.setAttribute("roles", loginDto.getBody().getRoles());

        }
        return ResponseEntity.ok(loginDto);
    }


    @PostMapping("/account/testretreivingLogin")
    public ResponseEntity Login(@RequestBody String correlationId) throws ExecutionException, InterruptedException, JsonProcessingException {

        //wait for response to be saved to db
        Future<String> response = null;
        try {
            response = GetResponseOrWait(correlationId);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        String res = response.get();

        // save user to Db with expiration
        LoginResponseDto myDto = null;
        if (res != null) {

            myDto = objectMapper.readValue(res, LoginResponseDto.class);

            session.setAttribute("username", myDto.getBody().getUsername());
            session.setAttribute("roles", myDto.getBody().getRoles());
            session.setAttribute("LoginExpiration", LocalDateTime.now().plusMinutes(30));
        }
        return ResponseEntity.ok(myDto);
    }

    @PostMapping("/account/signup")
    public ResponseEntity Signup(@RequestBody SignUpRequestDto request) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        //send request
        String correlationId = securityMqService.SendSignUpMessageToMqServer(request);
        //wait for response to be saved to db
        var response = GetResponseOrWait(request.getUsername());
        String res = response.get();

        SignUpResponseDto signupDto = null;
        if (res != null) {
            signupDto = objectMapper.readValue(res, SignUpResponseDto.class);
            signupDto.setBodyDto(null);
        }
        return ResponseEntity.ok(signupDto);
    }

    @PostMapping("/account/testretreivingsignup")
    public ResponseEntity Signup(@RequestBody String correlationId) throws ExecutionException, InterruptedException, JsonProcessingException {

        //wait for response to be saved to db
        Future<String> response = null;
        try {
            response = GetResponseOrWait(correlationId);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        String res = response.get();

        // save user to Db with expiration
        LoginResponseDto myDto = null;
        if (res != null) {
            myDto = objectMapper.readValue(res, LoginResponseDto.class);
        }
        return ResponseEntity.ok(myDto);
    }

    @GetMapping("/account/logout")
    public void Logout(){
        session.invalidate();
    }

}
