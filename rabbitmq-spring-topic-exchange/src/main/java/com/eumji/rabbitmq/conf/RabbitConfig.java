package com.eumji.rabbitmq.conf;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FILE: com.eumji.rabbitmq.conf.RabbitConfig.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/18
 * TIME: 14:42
 */
@Configuration
public class RabbitConfig {
    private final String TOPIC_EXCHANGE = "spring_topic_exchange";
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
    public TopicExchange topic(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindColor(TopicExchange topic,Queue queue){
        return BindingBuilder.bind(queue).to(topic).with("*.orange.*");
    }

    @Bean
    public Binding bindWay(TopicExchange topic,Queue queue2){
        return BindingBuilder.bind(queue2).to(topic).with("lazy.#");
    }

    @Bean
    public Binding bindAnimal(TopicExchange topic,Queue queue3){
        return BindingBuilder.bind(queue3).to(topic).with("*.*.rabbit");
    }


}
