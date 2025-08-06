package com.hitcake.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String HELLOWORLD_QUEUE = "helloworld.queue";
    public static final String WORK_QUEUE = "work.queue";
    public static final String FANOUT_EXCHANGE = "fanout.exchange";
    public static final String FANOUT_QUEUE1 = "fanout.queue1";
    public static final String FANOUT_QUEUE2 = "fanout.queue2";

    /**
     * Helloworld 模式 1对1
     * @return
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
}
