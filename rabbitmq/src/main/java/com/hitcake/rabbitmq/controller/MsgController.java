package com.hitcake.rabbitmq.controller;

import com.hitcake.rabbitmq.config.RabbitMQConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbitmq")
public class MsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("helloworld/send")
    public String sendHelloworld(@RequestParam String msg) {
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.HELLOWORLD_QUEUE, msg);
        return "ok";
    }
    @GetMapping("workqueue/send")
    public String sendWorkQueue(@RequestParam String msg) {
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.WORK_QUEUE, msg);
        return "ok";
    }
    @GetMapping("fanout/send")
    public String sendFanout(@RequestParam String msg) {
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.FANOUT_EXCHANGE, "", msg);
        return "ok";
    }
    @GetMapping("direct/send")
    public String sendDirect1(@RequestParam String msg,@RequestParam String color) {
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.DIRECT_EXCHANGE, color, msg);
        return "ok";
    }

}
