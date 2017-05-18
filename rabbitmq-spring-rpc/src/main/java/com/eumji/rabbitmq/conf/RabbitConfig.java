package com.eumji.rabbitmq.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FILE: com.eumji.rabbitmq.conf.RabbitConfig.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/18
 * TIME: 15:11
 */
@Configuration
public class RabbitConfig {


    @Bean
    public DirectExchange direct(){
        return new DirectExchange("rabbit-spring-rpc");
    }

    @Bean
    public Queue queue(){
        return new Queue("spring_rpc_queue");
    }

    @Bean
    public Binding bindingQueue(DirectExchange direct,Queue queue){
        return BindingBuilder.bind(queue).to(direct).with("spring-rabbit-rpc-key");
    }
}
