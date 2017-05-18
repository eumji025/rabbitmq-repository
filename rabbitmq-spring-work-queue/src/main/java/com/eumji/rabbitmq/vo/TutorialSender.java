package com.eumji.rabbitmq.vo;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private Queue queue;

    public void send(){
        for (int i = 0; i < 10; i++) {
            String message = "message "+ i;
            rabbitTemplate.convertAndSend(queue.getName(),message);
            System.out.println("sender will send message: "+message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
