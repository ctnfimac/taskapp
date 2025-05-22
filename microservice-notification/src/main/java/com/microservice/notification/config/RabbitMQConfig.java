package com.microservice.notification.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "tareas-completadas";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, false); // false = no durable
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}