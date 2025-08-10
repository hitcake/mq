package com.hitcake.rmqc.listener;

import com.hitcake.rmqc.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.queue2")
public class FanoutListener2 {
    @RabbitHandler
    public void process(User user) {
        System.out.println("FanoutListener2 消费者收到消息  : " + user);
    }
}
