package com.hitcake.rmqc.listener;

import com.hitcake.rmqc.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "dead.letter.queue")
public class DeadLetterListener {
    @RabbitHandler
    public void process(User user) {
        System.out.println("DeadLetterListener 消费者收到死信消息  : " + user);
    }
}
