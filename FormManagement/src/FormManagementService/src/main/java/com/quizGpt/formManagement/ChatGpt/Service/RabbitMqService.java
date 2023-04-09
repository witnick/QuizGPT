package com.quizGpt.formManagement.ChatGpt.Service;

import com.quizGpt.formManagement.ChatGpt.Dto.GptServiceQuestionDto;
import com.quizGpt.formManagement.ChatGpt.Dto.GptServiceResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements  IMessageProducer, IMessageConsumer {

    @Value("${rabbitmq.gpt.exchange.name}")
    private String gptExchangeName;
    @Value("${rabbitmq.auth.exchange.name}")
    private String authExchangeName;

    @Value("${rabbitmq.gpt.routing.key}")
    private String gptRoutingKey;

    @Value("${rabbitmq.auth.routing.key.loginQuery}")
    private String authLoginRoutingKey;

    @Value("${rabbitmq.auth.routing.key.singupQuery}")
    private String authSignupRoutingKey;

    private RabbitTemplate rabbitTemplate;

    private static  final Logger LOGGER = LoggerFactory.getLogger(RabbitMqService.class);

    public RabbitMqService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void SendMessageToGptServer(String message) {
        LOGGER.info(String.format("Message sent -> %s , \r\n exchange sent -> %s, \r\n routingKey sent -> %s",message, gptExchangeName, gptRoutingKey));
        rabbitTemplate.convertAndSend(gptExchangeName, gptRoutingKey, message);
    }

    @Override
    public void SendMessageToGptServer(GptServiceQuestionDto message) {
        LOGGER.info(String.format("Message sent -> %s , \r\n exchange sent -> %s, \r\n routingKey sent -> %s",message, gptExchangeName, gptRoutingKey));
        rabbitTemplate.convertAndSend(gptExchangeName, gptRoutingKey, message);
    }

    @Override
    public void SendMessageToAuthServer(String message) {

    }

    @RabbitListener(queues = {"${rabbitmq.gpt.queue.response.name}"})
    @Override
    public GptServiceResponseDto ReadMessageFromGptServer(GptServiceResponseDto gptResponse) {
        return gptResponse;
    }
}
