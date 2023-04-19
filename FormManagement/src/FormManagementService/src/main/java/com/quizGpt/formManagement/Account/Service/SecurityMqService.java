package com.quizGpt.formManagement.Account.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizGpt.formManagement.Account.Entity.MqResponse;
import com.quizGpt.formManagement.Account.Repository.MqResponseRepository;
import com.quizGpt.formManagement.ChatGpt.Service.RabbitMqService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class SecurityMqService implements ISecurityMqService{
    @Value("${rabbitmq.auth.exchange.name}")
    private String authExchangeName;

    @Value("${rabbitmq.auth.routing.key.loginQuery}")
    private String authLoginRoutingKey;

    @Value("${rabbitmq.auth.routing.key.singupQuery}")
    private String authSignupRoutingKey;

    private RabbitTemplate rabbitTemplate ;
    private ModelMapper mapper;
    private MessageConverter messageConverter;
    private ObjectMapper jsonMapper;
    private final MqResponseRepository responseRepository;
    public SecurityMqService(RabbitTemplate rabbitTemplate,
                            ModelMapper mapper,
                             MessageConverter messageConverter,
                             MqResponseRepository responseRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
        this.messageConverter = messageConverter;
        this.jsonMapper =  new ObjectMapper();
        this.responseRepository = responseRepository;
    }

    private static  final Logger LOGGER = LoggerFactory.getLogger(RabbitMqService.class);

    public <LoginRequestDto> String SendLoginMessageToMqServer(LoginRequestDto message) {
        return SendMessageToMqServer(authLoginRoutingKey, message);
    }
    public <SignUpRequestDto> String SendSignUpMessageToMqServer(SignUpRequestDto message) {
        return SendMessageToMqServer(authSignupRoutingKey, message);
    }

    private <T> String SendMessageToMqServer(String routingKey, T message) {
        String json = null;
        try {
            json = jsonMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // Generate a correlation ID
        String correlationId = UUID.randomUUID().toString();

        // Set correlation ID in message properties
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setCorrelationId(correlationId);
        Message messageToSend = new Message(json.getBytes(), messageProperties);

        LOGGER.info(String.format("Message sent -> %s , \r\n exchange sent -> %s, \r\n routingKey sent -> %s", json.toString(), authExchangeName, routingKey));

        rabbitTemplate.convertAndSend(authExchangeName, routingKey, messageToSend);
        return correlationId;
    }

//    @RabbitListener(queues = {"${rabbitmq.auth.queue.login.name}"})
//    public void GetLoginMessageFromMqServer(Message message) {
//        SaveMessageToDb(message);
//    }
//
//    @RabbitListener(queues = {"${rabbitmq.auth.queue.signup.name}"})
//    public void GetSignUpMessageToMqServer(Message message) {
//        SaveMessageToDb(message);
//    }

    @RabbitListener(queues = {"${rabbitmq.auth.queue.login.name}"})
    public void GetLoginMessageFromMqServer(Object message) {
        try {
            SaveMessageToDb((Message)message);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = {"${rabbitmq.auth.queue.signup.name}"})
    public void GetSignUpMessageToMqServer(Object message) {
        try {
            SaveMessageToDb((Message)message);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private void SaveMessageToDb(Message message) throws UnsupportedEncodingException {
        String correlationId = message.getMessageProperties().getCorrelationId();
        if(correlationId == null){
            correlationId = UUID.randomUUID().toString();
        }
        // Handle user signup message
        String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
        LOGGER.info("Received message: " + message);

        ObjectMapper mapper = new ObjectMapper();


        // Get the message payload as byte array
        //byte[] payload = message.getBody();

        // Convert the byte array to string
        //String jsonString = new String(payload);

        String jsonString = new String(message.getBody(), "UTF-8");
        try {
            LOGGER.info(String.format("correlationId saved -> %s", correlationId));
            LOGGER.info(String.format("object saved -> %s", jsonString));

            responseRepository.save( new MqResponse(correlationId, jsonString));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
