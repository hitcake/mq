package com.hitcake.rmqc.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitcake.rmqc.model.User;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "rpc.req.queue")
public class RpcListener {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @RabbitHandler
    public void process(User user, Channel channel, Message message) throws IOException {
        String correlationId = message.getMessageProperties().getCorrelationId();
        String replyTo = message.getMessageProperties().getReplyTo();
        System.out.printf("correlationId: %s replyTo: %s" , correlationId, replyTo);
        AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                .Builder()
                .correlationId(correlationId)
                .build();

        System.out.println("RpcListener收到消息: " + user);
        user.setAge(user.getAge() + 1);
        try {
            channel.basicPublish("",replyTo, replyProps, objectMapper.writeValueAsBytes(user));
        }catch (Exception e){
            channel.basicPublish("",replyTo, replyProps, e.getMessage().getBytes());
        }

    }
}
