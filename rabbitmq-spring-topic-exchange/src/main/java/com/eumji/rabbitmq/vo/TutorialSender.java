package com.eumji.rabbitmq.vo;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * FILE: com.eumji.rabbitmq.vo.TutorialSender.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/17
 * TIME: 20:54
 */
@Component
public class TutorialSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topic;
    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};
    public void send(){
        for (int i = 0; i < 10; i++) {
            String routingKey = getRoutingKey();
            String message =routingKey+ " message "+ i;
            try {
                Thread.sleep(1000);
                rabbitTemplate.convertAndSend(topic.getName(),routingKey,message);
                System.out.println("sender will send "+routingKey+" message: "+message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getRoutingKey() {
        int num = new Random().nextInt(6);
       return keys[num];
    }
}
