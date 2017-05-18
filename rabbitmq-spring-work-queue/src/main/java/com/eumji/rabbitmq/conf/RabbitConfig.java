package com.eumji.rabbitmq.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置文件
 * FILE: com.eumji.rabbitmq.conf.RabbitConfig.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/18
 * TIME: 12:02
 */
@Configuration
public class RabbitConfig {
    final static String queueName = "spring-boot-work-queue";
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }



}
