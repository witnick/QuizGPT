package com.quizGpt.formManagement.ChatGpt.Controller;

import com.quizGpt.formManagement.ChatGpt.Dto.GptServiceQuestionDto;
import com.quizGpt.formManagement.ChatGpt.Service.RabbitMqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ChatGptController {

    private RabbitMqService producer;


    public ChatGptController(RabbitMqService producer) {
        this.producer = producer;
    }

    @PostMapping("/queryGpt")
    public ResponseEntity<String> sendMessage(@RequestBody GptServiceQuestionDto question){
        producer.SendMessageToGptServer(question);
        return ResponseEntity.ok("Message sent to RabbbitMQ gpt_exchange ...");
    }

    @GetMapping("/publishMessage")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        producer.SendMessageToGptServer(message);
        return ResponseEntity.ok("Message sent to RabbbitMQ ...");
    }
}
