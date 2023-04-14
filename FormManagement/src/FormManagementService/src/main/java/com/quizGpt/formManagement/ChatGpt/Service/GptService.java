package com.quizGpt.formManagement.ChatGpt.Service;

import com.quizGpt.formManagement.ChatGpt.ConversationRepository;
import com.quizGpt.formManagement.ChatGpt.Dto.ConversationDto;
import com.quizGpt.formManagement.ChatGpt.Entity.Conversation;
import com.quizGpt.formManagement.ChatGpt.Entity.GptServiceResponse;
import com.quizGpt.formManagement.ChatGpt.Exception.ConversationNotFoundException;
import com.quizGpt.formManagement.Quiz.Exception.QuizNotFoundException;
import com.quizGpt.formManagement.Quiz.Repository.QuizRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class GptService implements IGptService{
    private final QuizRepository quizRepository;

    private final ConversationRepository conversationRepository;
    private final ModelMapper mapper;

    public GptService(QuizRepository quizRepository, ConversationRepository conversationRepository, ModelMapper mapper) {
        this.conversationRepository = conversationRepository;
        this.mapper = mapper;
        this.quizRepository = quizRepository;
    }

    @Override
    public ConversationDto GetGptServiceConversation(Long conversationId ) throws ConversationNotFoundException {
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new ConversationNotFoundException("Conversation not found with id: " + conversationId));
        return  mapper.map(conversation, ConversationDto.class);
    }

    @Override
    public Conversation CreateOrReadConversationByQuizId(Long quizId) throws QuizNotFoundException {

        var quiz = quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException("Quiz not found with id: " + quizId));
        var conversation = quiz.getGptConversation();
        if(conversation == null){
            quiz.setGptConversation(new Conversation(null,quizId, LocalDateTime.now(),LocalDateTime.now(),null,null));
            quiz = quizRepository.saveAndFlush(quiz);
            conversation = quiz.getGptConversation();
        }
        return conversation;
    }

    @Override
    public Conversation SaveConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public void DeleteConversation(Long id) {
        conversationRepository.deleteById(id);
    }
}
