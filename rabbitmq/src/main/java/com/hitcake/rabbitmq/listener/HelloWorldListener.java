package com.hitcake.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
@RabbitListener(queues = "helloworld.queue")
public class HelloWorldListener {
    @RabbitHandler
    public void process(String testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage);
    }
}
