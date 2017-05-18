package com.eumji.rabbitmq.vo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * FILE: com.eumji.rabbitmq.vo.TutorialReseiver.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/17
 * TIME: 20:52
 */
@Component
public class TutorialReseiver2 {

    @RabbitListener(queues = "#{queue2.name}")
    public void receive(String message) {
        System.out.println(" receiver2 Received error message : '" + message + "'");
    }
}
