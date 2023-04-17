package com.quizGpt.formManagement.Account.Service;

import com.quizGpt.formManagement.Account.Entity.MqResponse;
import com.quizGpt.formManagement.Account.Repository.MqResponseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService{
    private MqResponseRepository mqResponseRepository;

    public AccountService(MqResponseRepository mqResponseRepository){
        this.mqResponseRepository = mqResponseRepository;
    }

    public MqResponse FindMqResponseByCorrelationId(String correlationId) {
        // Use the findOne() method to retrieve the MqResponse entity by CorrelationId
        Optional<MqResponse> optionalMqResponse = mqResponseRepository.findById(correlationId);

        // Check if the entity was found and return it, otherwise return null or throw an exception as needed
        if (optionalMqResponse.isPresent()) {
            return optionalMqResponse.get();
        } else {
            return null; // or throw an exception
        }
    }


    public void Delete(MqResponse response) {
        mqResponseRepository.delete(response);
    }
}
