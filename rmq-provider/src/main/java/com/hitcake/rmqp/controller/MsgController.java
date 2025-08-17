package com.hitcake.rmqp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitcake.rmqp.config.RabbitMQConfiguration;
import com.hitcake.rmqp.model.User;
import com.rabbitmq.client.AMQP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rabbitmq")
public class MsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("helloworld/send")
    public String sendHelloworld(@RequestBody User user) {
        try{
            //String json = objectMapper.writeValueAsString(user);
            //rabbitTemplate.convertAndSend(RabbitMQConfiguration.HELLOWORLD_QUEUE, json.getBytes(StandardCharsets.UTF_8));
            // 直接发送json字符串, 没有设置contentType, 会被当成String处理
            // HelloWorldListener.process(String s)
            //rabbitTemplate.convertAndSend(RabbitMQConfiguration.HELLOWORLD_QUEUE, json);
            // 通过Message发送，设置了ContentType为application/json, 会当成Map接收
            // HelloWorldListener.process(Map map)
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.HELLOWORLD_QUEUE, buildMessage(user));
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("helloworld/sendlist")
    public String sendHelloworldList(@RequestBody List<User> userList) {
        try{
            String json = objectMapper.writeValueAsString(userList);
            // 通过Message发送list，设置了ContentType为application/json, 会当成list接收
            // HelloWorldListener.process(List list)
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType("application/json");
            Message message = new Message(json.getBytes(),messageProperties);
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.HELLOWORLD_QUEUE, message);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("workqueue/send")
    public String sendWorkQueue(@RequestBody User user) {
        try{

            rabbitTemplate.convertAndSend(RabbitMQConfiguration.WORK_QUEUE, buildMessage(user));
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }

    }
    @PostMapping("fanout/send")
    public String sendFanout(@RequestBody User user) {
        try{

            rabbitTemplate.convertAndSend(RabbitMQConfiguration.FANOUT_EXCHANGE, "", buildMessage(user));
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }

    }
    @PostMapping("direct/send")
    public String sendDirect1(@RequestBody User user) {
        try{

            rabbitTemplate.convertAndSend(RabbitMQConfiguration.DIRECT_EXCHANGE, user.getSex(), buildMessage(user));
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }

    }
    @PostMapping("topic/send")
    public String sendTopic(@RequestBody User user,@RequestParam String routingKey) {
        try{

            rabbitTemplate.convertAndSend(RabbitMQConfiguration.TOPIC_EXCHANGE, routingKey, buildMessage(user));
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("headers/send")
    public String sendHeaders(@RequestBody User user,@RequestParam String headerKey,@RequestParam String headerValue) {
        try{
            String json = objectMapper.writeValueAsString(user);
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            messageProperties.getHeaders().put("__TypeId__","user");
            messageProperties.getHeaders().put(headerKey,headerValue);
            Message message = new Message(json.getBytes(),messageProperties);
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.HEADERS_EXCHANGE, "", message);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("rpc/send")
    public String sendRpc(@RequestBody User user) {
        try{
            String corrId = UUID.randomUUID().toString();
            String json = objectMapper.writeValueAsString(user);
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            messageProperties.getHeaders().put("__TypeId__","user");
            messageProperties.setCorrelationId(corrId);
            messageProperties.setReplyTo(RabbitMQConfiguration.RPC_REPLY_QUEUE);
            Message message = new Message(json.getBytes(),messageProperties);
            Message reply = rabbitTemplate.sendAndReceive(RabbitMQConfiguration.RPC_EXCHANGE, "req", message);
            if (reply != null) {
                String resp = new String(reply.getBody());
                user = objectMapper.readValue(resp, User.class);
                return "rpc调用结果: "+ objectMapper.writeValueAsString(user);
            } else {
                return "rpc调用结果: null";
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }

    private Message buildMessage(User user) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(user);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        // 加上这个header，在consumer端会被转换成User对象,不加转化为Map
        /*
        消费端配置 Jackson2JsonMessageConverter
        //消费端配置映射
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("user", User.class);
         */
        messageProperties.getHeaders().put("__TypeId__","user");
        return new Message(json.getBytes(),messageProperties);
    }

}
