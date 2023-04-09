package com.quizGpt.formManagement.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    //keys
    @Value("${rabbitmq.gpt.routing.key}")
    private String gptQueryKey;
    @Value("${rabbitmq.auth.routing.key.loginQuery}")
    private String authLoginQueryKey;
    @Value("${rabbitmq.auth.routing.key.singupQuery}")
    private String authSignupQueryKey;

    //Queues to read from
    @Value("${rabbitmq.gpt.queue.response.name}")
    private String gptQueryQueueName;
    @Value("${rabbitmq.auth.queue.login.name}")
    private String authLoginResponseQueueName;
    @Value("${rabbitmq.auth.queue.signup.name}")
    private String authSignupResponseQueueName;

    //Queues
    @Bean
    public Queue GptResponseQueue(){
        return new Queue(gptQueryQueueName);
    }
    @Bean
    public Queue AuthLoginResponseQueue(){
        return new Queue(authLoginResponseQueueName);
    }
    @Bean
    public Queue AuthSignupResponseQueue(){
        return new Queue(authSignupResponseQueueName);
    }


    @Value("${rabbitmq.gpt.exchange.name}")
    private String gptExchangeName;
    @Value("${rabbitmq.auth.exchange.name}")
    private String authExchangeName;
    @Bean
    public DirectExchange GptExchange(){
        return new DirectExchange(gptExchangeName);
    }
    @Bean
    public DirectExchange AuthExchange(){
        return new DirectExchange(authExchangeName);
    }

    @Bean
    //binding between queue and exchange using routing key
    public Binding GptBinding(){
        return BindingBuilder
                .bind(GptExchange())
                .to(GptExchange())
                .with(gptQueryKey);
    }

    @Bean
    //binding between queue and exchange using routing key
    public Binding LoginBinding(){
        return BindingBuilder
                .bind(AuthLoginResponseQueue())
                .to(AuthExchange())
                .with(authLoginQueryKey);
    }

    @Bean
    //binding between queue and exchange using routing key
    public Binding SignUpBinding(){
        return BindingBuilder
                .bind(AuthSignupResponseQueue())
                .to(AuthExchange())
                .with(authSignupQueryKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
