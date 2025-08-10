package com.hitcake.rmqc.listener;

import com.hitcake.rmqc.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.queue1")
public class TopicQueueListener1 {
    @RabbitHandler
    public void receive(User user) {
        System.out.println("TopicQueueListener1 routing key: china.# 收到消息：" + user);
    }
}
