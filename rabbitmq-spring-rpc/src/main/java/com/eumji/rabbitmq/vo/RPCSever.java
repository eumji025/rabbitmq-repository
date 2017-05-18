package com.eumji.rabbitmq.vo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * FILE: com.eumji.rabbitmq.vo.RPCSever.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/18
 * TIME: 15:15
 */
@Component
public class RPCSever {

    @RabbitListener(queues = "spring_rpc_queue")
    public String response(String message) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("server get the client request :"+message);

        return "hello client,Server has get your request:"+message;
    }
}
