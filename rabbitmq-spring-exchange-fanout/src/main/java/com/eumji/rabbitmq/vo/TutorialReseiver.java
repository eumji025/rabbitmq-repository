package com.eumji.rabbitmq.vo;

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
@RabbitListener(queues = "spring-boot-work-queue")
public class TutorialReseiver {

    @RabbitListener(queues = "#{queue.name}")
    public void receive1(String message) throws InterruptedException {
        receive(message, 1);
    }

    @RabbitListener(queues = "#{queue2.name}")
    public void receive2(String message) throws InterruptedException {
        receive(message, 2);
    }

    public void receive(String message, int i) {
        try {
            Thread.sleep(1000);
            System.out.println(" receiver"+i+" Received message : '" + message + "'");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
