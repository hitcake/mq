//package com.hitcake.rmqc.listener;
//
//import com.hitcake.rmqc.model.User;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//@Component
//@RabbitListener(queues = "helloworld.queue")
//public class HelloWorldListener {
//
//    /**
//     * 发送byte数组, 没有设置contentType, 会被当成byte[]处理
//     * @param bytes
//     * @throws Exception
//     */
//    @RabbitHandler
//    public void process(byte[] bytes) throws Exception {
//        System.out.println("Helloworld Receiver消费者收到消息 byte[] : " + new String(bytes));
//    }
//    /**
//     * 发送String, 没有设置contentType, 会被当成String处理
//     * @param msg
//     * @throws Exception
//     */
//    @RabbitHandler
//    public void process(String msg) throws Exception {
//        System.out.println("Helloworld Receiver消费者收到消息 String : " + msg);
//    }
//    /**
//     * 通过Message发送，设置了ContentType为application/json, 会当成Map接收
//     * @param user
//     * @throws Exception
//     */
//    @RabbitHandler
//    public void process(Map user) throws Exception {
//        System.out.println("Helloworld Receiver消费者收到消息  Map : " + user);
//    }
//
//    /**
//     * 通过Message发送list，设置了ContentType为application/json, 会当成list接收
//     * @param list
//     * @throws Exception
//     */
//    @RabbitHandler
//    public void process(List list) throws Exception {
//        System.out.println("Helloworld Receiver消费者收到消息 List : " + list);
//    }
//
//    @RabbitHandler
//    public void process(User user) throws Exception {
//        System.out.println("Helloworld Receiver消费者收到消息  User : " + user);
//    }
//}
