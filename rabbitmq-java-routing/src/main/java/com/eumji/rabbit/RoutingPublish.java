package com.eumji.rabbit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 *
 * exchange类型：
 * DIRECT("direct"), FANOUT("fanout"), TOPIC("topic"), HEADERS("headers")
 *
 * FILE: com.eumji.rabbit.PublishWorker.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/16
 * TIME: 21:20
 */
public class RoutingPublish {
    private static Logger logger = LoggerFactory.getLogger(RoutingPublish.class);
    private static final String EXCHANGE_NAME = "routing_worker";
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            //设置为fanout
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            for (int i = 0; i < 10; i++) {
                String message = getMessage();
                String severity = getSeverity();
                System.out.println("publish worker will send "+severity+" message:"+message);
                channel.basicPublish(EXCHANGE_NAME,severity,null,getMessage().getBytes("UTF-8"));
            }

        } catch (IOException e) {
            logger.error("connect io exception");
            e.printStackTrace();
        } catch (TimeoutException e) {
            logger.error("connect timeout exception");
            e.printStackTrace();
        }finally {
            channel.close();
            connection.close();
        }
    }

    private static String getMessage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(System.currentTimeMillis());
    }

    private static String getSeverity() {
        int num = new Random().nextInt(3);
        switch (num){
            case 0:return "info";
            case 1:return "warning";
            case 2:return "error";
        }
        return null;
    }

}
