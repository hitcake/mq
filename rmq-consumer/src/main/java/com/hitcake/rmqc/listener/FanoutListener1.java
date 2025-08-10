package com.hitcake.rmqc.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitcake.rmqc.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.queue1")
public class FanoutListener1 {
    @RabbitHandler
    public void process(User user) {
        System.out.println("FanoutListener1 消费者收到消息  : " + user);
    }
}
