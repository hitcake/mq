package com.hitcake.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "work.queue")
public class WorkQueueListener1 {

    @RabbitHandler
    public void process(String testMessage) {
        System.out.println("WorkQueueListener1 消费者收到消息  : " + testMessage);
    }
}
