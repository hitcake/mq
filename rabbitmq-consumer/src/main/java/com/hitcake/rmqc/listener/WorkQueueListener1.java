package com.hitcake.rmqc.listener;

import com.hitcake.rmqc.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "work.queue")
public class WorkQueueListener1 {

    @RabbitHandler
    public void process(User user) {
        System.out.println("WorkQueueListener1 消费者收到消息  : " + user);
    }
}
