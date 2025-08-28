package com.hitcake.rmqc.listener;

import com.hitcake.rmqc.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "headers.queue3")
public class HeadersListener3 {
    @RabbitHandler
    public void process(User user) {
        System.out.println("HeadersListener3收到消息: "+user);
    }
}
