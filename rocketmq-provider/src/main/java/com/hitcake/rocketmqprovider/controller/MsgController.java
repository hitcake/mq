package com.hitcake.rocketmqprovider.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rocketmq")
public class MsgController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    public String send(@RequestParam  String msg) {
        rocketMQTemplate.convertAndSend("test-topic-1", msg);
        return "ok";
    }
}
