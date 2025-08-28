package com.hitcake.rmqp.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitMQConfiguration {

    public static final String HELLOWORLD_QUEUE = "helloworld.queue";
    public static final String WORK_QUEUE = "work.queue";
    public static final String FANOUT_EXCHANGE = "fanout.exchange";
    public static final String FANOUT_QUEUE1 = "fanout.queue1";
    public static final String FANOUT_QUEUE2 = "fanout.queue2";

    public static final String DIRECT_EXCHANGE = "direct.exchange";
    public static final String DIRECT_QUEUE1 = "direct.queue1";
    public static final String DIRECT_QUEUE2 = "direct.queue2";

    public static final String TOPIC_EXCHANGE = "topic.exchange";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";

    public static final String HEADERS_EXCHANGE = "headers.exchange";
    public static final String HEADERS_QUEUE1 = "headers.queue1";
    public static final String HEADERS_QUEUE2 = "headers.queue2";
    public static final String HEADERS_QUEUE3 = "headers.queue3";

    public static final String RPC_EXCHANGE = "rpc.exchange";
    public static final String RPC_REQ_QUEUE = "rpc.req.queue";
    public static final String RPC_REPLY_QUEUE = "rpc.reply.queue";

    public static final String DEAD_LETTER_EXCHANGE = "dead.letter.exchange";
    public static final String DEAD_LETTER_QUEUE = "dead.letter.queue";

    /**
     * Helloworld 模式 1对1
     */
    @Bean
    public Queue helloWorldQueue() {
        QueueBuilder queueBuilder = QueueBuilder.durable(HELLOWORLD_QUEUE);
        queueBuilder.withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        queueBuilder.withArgument("x-dead-letter-routing-key", "dead");
        queueBuilder.withArgument("x-message-ttl", 60000);
        queueBuilder.withArgument("x-max-length", 10);
        return queueBuilder.build();
    }

    /**
     * workqueue 模式 1对N
     *
     */
    @Bean
    public Queue workQueue() {
        return new Queue(WORK_QUEUE, true);
    }

    /**
     * fanout 模式 广播
     *
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE1, true);
    }
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE2, true);
    }
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE,true,false);
    }
     @Bean
    public Binding bindingFanoutQueue1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingFanoutQueue2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    /**
     * direct 模式 定向
     */
    @Bean
    public Queue directQueue1() {
        return new Queue(DIRECT_QUEUE1, true);
    }
    @Bean
    public Queue directQueue2() {
        return new Queue(DIRECT_QUEUE2, true);
    }
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE,true,false);
    }
     @Bean
    public Binding bindingDirectQueue1() {
         return BindingBuilder.bind(directQueue1()).to(directExchange()).with("male");
    }
    @Bean
    public Binding bindingDirectQueue2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("female");
    }

    /**
     * topic 模式 通配符
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE1, true);
    }
    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE2, true);
    }
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE,true,false);
    }
     @Bean
    public Binding bindingTopicQueue1() {
         return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("china.#");
    }
    @Bean
    public Binding bindingTopicQueue2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("#.news");
    }

    /**
     * headers 模式 复杂匹配
     */
    @Bean
    public Queue headersQueue1() {
        return  new Queue(HEADERS_QUEUE1, true);
    }
    @Bean
    public Queue headersQueue2() {
        return new Queue(HEADERS_QUEUE2, true);
    }
    @Bean
    public Queue headersQueue3() {
        return new Queue(HEADERS_QUEUE3, true);
    }
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE, true, false);
    }
    @Bean
    public Binding bindingHeadersQueue1() {
        return BindingBuilder.bind(headersQueue1()).to(headersExchange()).where("company").matches("apple");
    }
    @Bean
    public Binding bindingHeadersQueue2() {
        Map<String,Object> map = new HashMap<>();
        map.put("company","apple");
        map.put("nationality","china");
        return BindingBuilder.bind(headersQueue2()).to(headersExchange()).whereAny(map).match();
    }
    @Bean
    public Binding bindingHeadersQueue3() {
        return BindingBuilder.bind(headersQueue3()).to(headersExchange()).whereAny("company","language").exist();
    }

    /**
     * rpc 模式 远程调用
     */
    @Bean
    public Queue rpcReqQueue() {
        return new Queue(RPC_REQ_QUEUE, true);
    }
    @Bean
    public Queue rpcReplyQueue() {
        return new Queue(RPC_REPLY_QUEUE, true);
    }
    @Bean
    public DirectExchange rpcExchange() {
        return new DirectExchange(RPC_EXCHANGE,true,false);
    }
    @Bean
    public Binding bindingRpcReqQueue() {
        return BindingBuilder.bind(rpcReqQueue()).to(rpcExchange()).with("req");
    }
    @Bean
    public Binding bindingRpcReplyQueue() {
        return BindingBuilder.bind(rpcReplyQueue()).to(rpcExchange()).with("reply");
    }

    /**
     * 死信队列
     */
    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE, true);
    }
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE,true,false);
    }
    @Bean
    public Binding bindingDeadLetterQueue() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("dead");
    }
}
