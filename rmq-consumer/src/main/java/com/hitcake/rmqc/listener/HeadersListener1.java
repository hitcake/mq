package com.hitcake.rmqc.listener;

import com.hitcake.rmqc.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "headers.queue1")
public class HeadersListener1 {
    @RabbitHandler
    public void process(User user) {
        System.out.println("HeadersListener1收到消息: "+user);
    }
}
