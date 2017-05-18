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
public class TutorialReseiver {


    @RabbitListener(queues = "#{queue.name}")
    public void receiver(String message) throws InterruptedException {
        receive(message,"*.orange.*");
    }
    @RabbitListener(queues = "#{queue2.name}")
    public void receiver2(String message) throws InterruptedException {
        receive(message, "lazy.#");
    }
    @RabbitListener(queues = "#{queue3.name}")
    public void receiver3(String message) throws InterruptedException {
        receive(message, "*.*.rabbit");
    }

    public void receive(String message, String queue) {
        try {
            Thread.sleep(1000);
            System.out.println(" receiver Received the queue "+queue+" message : '" + message + "'");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
