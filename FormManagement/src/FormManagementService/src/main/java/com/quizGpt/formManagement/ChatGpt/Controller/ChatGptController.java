package com.quizGpt.formManagement.ChatGpt.Controller;

import com.quizGpt.formManagement.ChatGpt.Dto.ConversationDto;
import com.quizGpt.formManagement.ChatGpt.Dto.GptServiceQuestionDto;
import com.quizGpt.formManagement.ChatGpt.Entity.Conversation;
import com.quizGpt.formManagement.ChatGpt.Entity.GptServiceQuestion;
import com.quizGpt.formManagement.ChatGpt.Exception.ConversationNotFoundException;
import com.quizGpt.formManagement.ChatGpt.Service.GptService;
import com.quizGpt.formManagement.ChatGpt.Service.RabbitMqService;
import com.quizGpt.formManagement.Quiz.Dto.QuizDto;
import com.quizGpt.formManagement.Quiz.Entity.Quiz;
import com.quizGpt.formManagement.Quiz.Exception.QuizNotFoundException;
import com.quizGpt.formManagement.Quiz.Services.QuizService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ChatGptController {

    private RabbitMqService rabbitMqService;
    private GptService gptService;

    private ModelMapper mapper;

    private QuizService quizService;
    private HttpSession session;
    private String username;

    public ChatGptController(RabbitMqService rabbitMqService, GptService gptService, ModelMapper mapper, QuizService quizService, HttpSession session) {
        this.rabbitMqService = rabbitMqService;
        this.gptService = gptService;
        this.mapper = mapper;
        this.quizService = quizService;
        this.session = session;

    }

    @GetMapping("/conversation/{id}")
    public ResponseEntity<ConversationDto> GetConversationMessage(@PathVariable("id") Long id) throws ConversationNotFoundException {
        ConversationDto conversation = gptService.GetGptServiceConversation(id);
        return ResponseEntity.ok(conversation);
    }

    @DeleteMapping("/conversation/{id}")
    public ResponseEntity<ConversationDto> DeleteConversationMessage(@PathVariable("id") Long id) throws ConversationNotFoundException {
        gptService.DeleteConversation(id);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/queryGpt")
    public ResponseEntity<ConversationDto> sendMessage(@RequestBody QuizDto quizDto) throws QuizNotFoundException {
        UpdateSessionUsername();
        quizDto.setUsername(this.username);
        //create conversation if it doesn't exist
        Quiz quiz = quizService.SaveQuiz(mapper.map(quizDto, Quiz.class));
            Conversation conversation = gptService.CreateOrReadConversationByQuizId(quiz.getId());

        GptServiceQuestionDto questionDto = quizDto.getGptRequest();
        questionDto.setConvoId(conversation.getId());
        GptServiceQuestion question = mapper.map(questionDto, GptServiceQuestion.class);
        question.setConversationId(conversation.getId());
        //save question in conversation
        List<GptServiceQuestion> questions = conversation.getQuestions();
        if(questions == null)
            questions = new ArrayList<>();
        questions.add(question);
        conversation.setQuestions(questions);
        gptService.SaveConversation(conversation);
        //send message to queue
        rabbitMqService.SendMessageToGptServer(questionDto);
        //return conversation object with id
        ConversationDto conversationDto = mapper.map(conversation, ConversationDto.class);
        return ResponseEntity.ok(conversationDto);
    }

    private void UpdateSessionUsername(){
        if(this.session.isNew()) {
            this.session.setAttribute("username", "anonymous");
        }
        this.username = (String)this.session.getAttribute("username");
    }
}
