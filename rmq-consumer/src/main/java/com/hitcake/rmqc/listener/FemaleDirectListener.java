package com.hitcake.rmqc.listener;

import com.hitcake.rmqc.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct.queue2")
public class FemaleDirectListener {
    @RabbitHandler
    public void receive(User user) {
        System.out.println("FemaleDirectListener  : " + user);
    }
}
