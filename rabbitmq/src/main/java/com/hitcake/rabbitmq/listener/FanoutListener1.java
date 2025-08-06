package com.hitcake.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.queue1")
public class FanoutListener1 {
    @RabbitHandler
    public void process(String testMessage) {
        System.out.println("FanoutListener1 消费者收到消息  : " + testMessage);
    }
}
