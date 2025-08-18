package com.hitcake.rocketmqconsumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;


@Service
@RocketMQMessageListener(
        topic = "test-topic-1",
        consumerGroup = "Test_Group"
)
public class MQListenerConcurrently implements RocketMQListener<String> {


    @Override
    public void onMessage(String s) {
        System.out.println(s);
    }
}
