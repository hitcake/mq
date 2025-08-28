package com.hitcake.rmqc.listener;

import com.hitcake.rmqc.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "headers.queue2")
public class HeadersListener2 {
    @RabbitHandler
    public void process(User user) {
        System.out.println("HeadersListener2收到消息: "+user);
    }
}
