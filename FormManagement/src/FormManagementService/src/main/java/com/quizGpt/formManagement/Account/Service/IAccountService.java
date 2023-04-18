package com.quizGpt.formManagement.Account.Service;

import com.quizGpt.formManagement.Account.Entity.MqResponse;

public interface IAccountService {
    public MqResponse FindMqResponseByCorrelationId(String correlationId);

    MqResponse FindFirstByResponseContaining(String correlationId);
}
