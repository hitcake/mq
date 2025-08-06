package com.hitcake.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String HELLOWORLD_QUEUE = "helloworld.queue";
    public static final String WORK_QUEUE = "work.queue";

    @Bean
    public Queue helloWorldQueue() {
        return new Queue(HELLOWORLD_QUEUE, true);
    }

    @Bean
    public Queue workQueue() {
        return new Queue(WORK_QUEUE, true);
    }
}
