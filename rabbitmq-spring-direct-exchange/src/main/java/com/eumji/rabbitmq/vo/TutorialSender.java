package com.eumji.rabbitmq.vo;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
    private DirectExchange direct;

    public void send(){
        for (int i = 0; i < 10; i++) {
            String routingKey = getRoutingKey();
            String message =routingKey+ " message "+ i;
            try {
                Thread.sleep(1000);
                rabbitTemplate.convertAndSend(direct.getName(),routingKey,message);
                System.out.println("sender will send "+routingKey+" message: "+message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getRoutingKey() {
        int num = new Random().nextInt(3);
        switch (num){
            case 0: return "info";
            case 1: return "error";
            case 2: return "warning";
        }
        return null;
    }
}
