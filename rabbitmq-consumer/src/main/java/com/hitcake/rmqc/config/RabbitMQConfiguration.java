package com.hitcake.rmqc.config;


import com.hitcake.rmqc.model.User;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

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

    /**
     * RabbitTemplate配置
     */
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        // 设置消息转换器
//        template.setMessageConverter(contentTypeConverter());
//        return template;
//    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        //消费端配置映射
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("user", User.class);

        DefaultJackson2JavaTypeMapper jackson2JavaTypeMapper = new DefaultJackson2JavaTypeMapper();
        jackson2JavaTypeMapper.setIdClassMapping(idClassMapping);

        converter.setJavaTypeMapper(jackson2JavaTypeMapper);
        return converter;
    }

    /**
     * 内容类型转换适配器
     * 可以根据不同的内容类型选择不同的转换器
     */
//    @Bean
//    public ContentTypeDelegatingMessageConverter contentTypeConverter() {
//        ContentTypeDelegatingMessageConverter converter = new ContentTypeDelegatingMessageConverter();
//
//        // 注册JSON转换器
//        converter.addDelegate(MessageProperties.CONTENT_TYPE_JSON, new Jackson2JsonMessageConverter());
//        // 添加 XML 转换器
//        Jackson2XmlMessageConverter xmlConverter = new Jackson2XmlMessageConverter();
//        converter.addDelegate(MessageProperties.CONTENT_TYPE_XML, xmlConverter);
//        // 注册TEXT转换器
//        converter.addDelegate(MessageProperties.CONTENT_TYPE_TEXT_PLAIN, new SimpleMessageConverter());
//
//        return converter;
//    }

}
