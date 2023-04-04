package com.quizgpt.accountservice.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MessageQueueListener {

    @RabbitListener(queues = "user.signup.queue")
    public void receiveSignupMessage(Message message) {
        // Handle user signup message
    }

    @RabbitListener(queues = "user.signin.queue")
    public void receiveSigninMessage(Message message) {
        // Handle user signin message
    }
}
