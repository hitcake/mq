package com.hitcake.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct.queue2")
public class BlueDirectListener {
    @RabbitHandler
    public void receive(String msg) {
        System.out.println("BlueDirectListener  : " + msg);
    }
}
