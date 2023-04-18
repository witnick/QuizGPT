package com.quizGpt.formManagement.ChatGpt.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizGpt.formManagement.ChatGpt.Repository.ConversationRepository;
import com.quizGpt.formManagement.ChatGpt.Dto.GptServiceQuestionDto;
import com.quizGpt.formManagement.ChatGpt.Dto.GptServiceResponseDto;
import com.quizGpt.formManagement.ChatGpt.Entity.GptServiceResponse;

import com.quizGpt.formManagement.ChatGpt.Exception.ConversationNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements  IMessageProducer, IMessageConsumer {
    @Value("${rabbitmq.gpt.sender}")
    private String GPTSENDER;

    private RabbitTemplate rabbitTemplate;
    private ConversationRepository conversationRepository;
    private ModelMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(RabbitMqService.class);

    public RabbitMqService(RabbitTemplate rabbitTemplate, ConversationRepository conversationRepository, ModelMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.conversationRepository = conversationRepository;
        this.mapper = mapper;
    }
    @Value("${rabbitmq.gpt.exchange.name}")
    private String gptExchangeName;

    @Value("${rabbitmq.gpt.routing.key}")
    private String gptRoutingKey;

    private static  final Logger LOGGER = LoggerFactory.getLogger(RabbitMqService.class);

    @Override
    public void SendMessageToGptServer(String message) {
        LOGGER.info(String.format("Message sent -> %s , \r\n exchange sent -> %s, \r\n routingKey sent -> %s",message, gptExchangeName, gptRoutingKey));
        rabbitTemplate.convertAndSend(gptExchangeName, gptRoutingKey, message);
    }

    @Override
    public void SendMessageToGptServer(GptServiceQuestionDto message) {

        LOGGER.info(String.format("Message sent -> %s , \r\n exchange sent -> %s, \r\n routingKey sent -> %s",message, gptExchangeName, gptRoutingKey));
        message.setSender(GPTSENDER);
        rabbitTemplate.convertAndSend(gptExchangeName, gptRoutingKey, message);
    }

    @Override
    public void SendMessageToAuthServer(String message) {

    }

    @RabbitListener(queues = {"${rabbitmq.gpt.queue.response.name}"})

    public void ReadMessageFromGptServer(GptServiceResponseDto gptResponse) throws ConversationNotFoundException {
        com.fasterxml.jackson.databind.ObjectMapper jsonMapper =  new ObjectMapper();;
        String json = null;
        try {
            json = jsonMapper.writeValueAsString(gptResponse);
            LOGGER.info(String.format("GptServiceResponse received -> %s", json.toString()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        GptServiceResponse response = mapper.map(gptResponse, GptServiceResponse.class);
        Long conversationId = response.getConversationId();

        try {
            json = jsonMapper.writeValueAsString(response);
            LOGGER.info(String.format("response received -> %s", json.toString()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        var conversation = conversationRepository.findById(response.getConversationId()).orElseThrow(() -> new ConversationNotFoundException("Conversation not found with id: " + conversationId));;
        var responseList = conversation.getResponses();
        responseList.add(response);
        conversation.setResponses(responseList);
        conversationRepository.save(conversation);
    }
}
