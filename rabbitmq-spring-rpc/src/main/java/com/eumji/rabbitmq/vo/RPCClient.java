package com.eumji.rabbitmq.vo;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FILE: com.eumji.rabbitmq.vo.RPCClient.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/18
 * TIME: 15:19
 */
@Component
public class RPCClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange direct;

    public void  send() {
        String message = "hello,I'm client";
        System.out.println("client send message : "+message);
        String response = (String) rabbitTemplate.convertSendAndReceive(direct.getName(), "spring-rabbit-rpc-key", message);
        System.out.println("client get response :"+response);
    }

}

