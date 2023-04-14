package com.quizGpt.formManagement.Account.Service;

public interface ISecurityMqService {
    public  <T> void SendLoginMessageToMqServer(T message);
    public <T> void SendSignUpMessageToMqServer(T message);

}
