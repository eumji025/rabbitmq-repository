package com.eumji.rabbitmq.conf;

import com.eumji.rabbitmq.vo.TutorialReseiver;
import com.eumji.rabbitmq.vo.TutorialSender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


/**
 * FILE: com.eumji.rabbitmq.conf.TutorialConfig.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/17
 * TIME: 20:47
 */
@Configuration
public class TutorialConfig {

    @Bean
    public Queue hello(){
        return new Queue("hello_queue");
    }
}
