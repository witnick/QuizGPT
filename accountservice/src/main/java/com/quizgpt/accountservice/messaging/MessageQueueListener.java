package com.quizgpt.accountservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizgpt.accountservice.payload.request.LoginRequest;
import com.quizgpt.accountservice.payload.request.SignupRequest;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.quizgpt.accountservice.controllers.AuthController;

import java.nio.charset.StandardCharsets;

@Component
public class MessageQueueListener {
    final Logger logger = LoggerFactory.getLogger(MessageQueueListener.class);

    @Autowired
    AuthController authController;

    @RabbitListener(queues = "user.auth.signup")
    public void receiveSignupMessage(Message message)  {
        // Handle user signup message
        String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
        logger.info("Received message: " + message);
//        System.out.println("Received message: " + message);
//        System.out.println("Message Body: " + messageBody);

        ObjectMapper mapper = new ObjectMapper();
        SignupRequest signupRequest = null;
        try {
            signupRequest = mapper.readValue(messageBody, SignupRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        authController.registerUser(signupRequest);

    }

    @RabbitListener(queues = "user.auth.signin")
    public void receiveSigninMessage(Message message) {
        // Handle user signin message
        logger.info("Received message: " + message);
//        System.out.println("Received message: " + message);
//        System.out.println("Message Body: " + new String(message.getBody(), StandardCharsets.UTF_8));
//
        ObjectMapper mapper = new ObjectMapper();
        LoginRequest loginRequest = null;
        try {
            loginRequest = mapper.readValue(new String(message.getBody(), StandardCharsets.UTF_8), LoginRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        authController.authenticateUser(loginRequest);
    }
}
