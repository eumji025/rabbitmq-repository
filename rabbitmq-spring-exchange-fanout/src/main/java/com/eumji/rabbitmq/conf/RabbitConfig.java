package com.eumji.rabbitmq.conf;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 使用fanout的时候
 * 是不需要定义queue的名称的
 * FILE: com.eumji.rabbitmq.conf.RabbitConfig.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/18
 * TIME: 12:16
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue queue(){
        return new AnonymousQueue();
    }

    @Bean
    public Queue queue2(){
        return new AnonymousQueue();
    }

    @Bean
    public FanoutExchange fanout(){
        return new FanoutExchange("spring_fanout");
    }


    @Bean
    public Binding binding(FanoutExchange fanout, Queue queue) {
        return BindingBuilder.bind(queue).to(fanout);
    }

    @Bean
    public Binding binding2(FanoutExchange fanout, Queue queue2) {
        return BindingBuilder.bind(queue2).to(fanout);
    }


}
