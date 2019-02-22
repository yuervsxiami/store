package com.cnuip.user.config;

import com.cnuip.user.enums.RabbitConfigEnums;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangzhibin on 2018/4/12.
 */
@Configuration
public class RabbitConfig {

    /**
     * 处理新增用户
     */
    @Bean
    TopicExchange storeUserExchange() {
        return new TopicExchange(RabbitConfigEnums.STORE_USER_EXCHANGE.getName());
    }

    @Bean
    public Queue storeUserQueue() {
        return new Queue(RabbitConfigEnums.STORE_USER.getName());
    }

    @Bean
    Binding bindingStoreUserExchangeMessages(Queue storeUserQueue, TopicExchange storeUserExchange) {
        return BindingBuilder.bind(storeUserQueue).to(storeUserExchange).with(RabbitConfigEnums.STORE_USER.getName());
    }


    /**
     * 需求MQ(PMES TO STORE2)
     */
    @Bean
    TopicExchange ProfessorExchange() {
        return new TopicExchange(RabbitConfigEnums.PROFESSOR_EXCHANGE.getName());
    }

    @Bean
    public Queue professorQueue() {
        return new Queue(RabbitConfigEnums.PROFESSOR.getName());
    }

    @Bean
    Binding bindingProfessorExchangeMessages(Queue professorQueue, TopicExchange ProfessorExchange) {
        return BindingBuilder.bind(professorQueue).to(ProfessorExchange).with(RabbitConfigEnums.PROFESSOR.getName());
    }
}