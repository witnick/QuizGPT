package com.quizgpt.accountservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizgpt.accountservice.login.LoginController;
import com.quizgpt.accountservice.payload.LoginRequest;
import com.quizgpt.accountservice.user.UserController;
import com.quizgpt.accountservice.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MessageQueueListener {
    final Logger logger = LoggerFactory.getLogger(MessageQueueListener.class);

    @Autowired
    LoginController loginController;

    @Autowired
    UserController userController;

    @Autowired
    MessageQueueConfig messageQueueConfig;

    @RabbitListener(queues = "user.auth.signup")
    public void receiveSignupMessage(Message message)  {
        // Handle user signup message
        String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
        logger.info("Received message: " + message);

        ObjectMapper mapper = new ObjectMapper();
        UserDto userDto = null;
        try {
            userDto = mapper.readValue(messageBody, UserDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<?> response = userController.createUser(userDto);

        // Publish a message to a RabbitMQ exchange
        messageQueueConfig.rabbitTemplate().convertAndSend("user.auth.signup.response", response);
    }

    /*
    {
        "username" : "myusername",
        "password" : "mypassword",
        "email" : "myemail"
    }
    */

    @RabbitListener(queues = "user.auth.login")
    public void receiveLoginMessage(Message message) {
        // Handle user signin message
        logger.info("Received message: " + message);

        ObjectMapper mapper = new ObjectMapper();
        LoginRequest loginRequest = null;
        try {
            loginRequest = mapper.readValue(new String(message.getBody(), StandardCharsets.UTF_8), LoginRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ResponseEntity<?> response = loginController.authenticateUser(loginRequest);

        // Publish a message to a RabbitMQ exchange
        messageQueueConfig.rabbitTemplate().convertAndSend("user.auth.login.response", response);
    }

    // TODO Add logout functionality

    // TODO How to verify jwt
}
