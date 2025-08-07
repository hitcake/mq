package com.hitcake.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct.queue1")
public class RedDirectListener {
    @RabbitHandler
    public void receive(String msg) {
        System.out.println("RedDirectListener  : " + msg);
    }
}
