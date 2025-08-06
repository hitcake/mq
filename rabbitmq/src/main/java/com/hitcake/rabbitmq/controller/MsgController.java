package com.hitcake.rabbitmq.controller;

import com.hitcake.rabbitmq.config.RabbitMQConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rabbitmq")
public class MsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("helloworld/send")
    public String send(@RequestParam String msg) {
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.QUEUE, msg);
        return "ok";
    }

}
