package com.hitcake.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    /**
     * Helloworld 模式 1对1
     */
    @Bean
    public Queue helloWorldQueue() {
        return new Queue(HELLOWORLD_QUEUE, true);
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
         return BindingBuilder.bind(directQueue1()).to(directExchange()).with("red");
    }
    @Bean
    public Binding bindingDirectQueue2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("blue");
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
}
