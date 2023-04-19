package com.quizGpt.formManagement.Account.Service;

public interface ISecurityMqService {
    public  <T> String SendLoginMessageToMqServer(T message);
    public <T> String SendSignUpMessageToMqServer(T message);

}
