package com.hitcake.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.queue2")
public class TopicQueueListener2 {
    @RabbitHandler
    public void receive(String msg) {
        System.out.println("TopicQueueListener1 routing key: #.news 收到消息：" + msg);
    }
}
