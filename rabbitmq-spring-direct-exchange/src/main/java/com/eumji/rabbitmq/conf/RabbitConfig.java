package com.eumji.rabbitmq.conf;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FILE: com.eumji.rabbitmq.conf.RabbitConfig.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/18
 * TIME: 12:36
 */
@Configuration
public class RabbitConfig {
    final String DIRECT_EXCHANGE = "spring-direct-exchange";
    @Bean
    public Queue queue(){
        return new AnonymousQueue();
    }
    @Bean
    public Queue queue2(){
        return new AnonymousQueue();
    }
    @Bean
    public Queue queue3(){
        return new AnonymousQueue();
    }

    @Bean
    public DirectExchange direct(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding bindingInfo(DirectExchange direct, Queue queue){
        return BindingBuilder.bind(queue).to(direct).with("info");
    }

    @Bean
    public Binding bindingError(DirectExchange direct,Queue queue2){
        return BindingBuilder.bind(queue2).to(direct).with("error");
    }
    @Bean
    public Binding bindingWarning(DirectExchange direct,Queue queue3){
        return BindingBuilder.bind(queue3).to(direct).with("warning");
    }
}

