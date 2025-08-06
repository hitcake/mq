package com.hitcake.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String QUEUE = "helloworld.queue";
    @Bean
    public Queue helloWorldQueue() {
        return new Queue(QUEUE, true);
    }
}
