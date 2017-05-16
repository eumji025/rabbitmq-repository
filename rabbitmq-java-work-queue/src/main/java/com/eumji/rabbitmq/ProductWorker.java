package com.eumji.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者 --sender
 *
 * FILE: com.eumji.rabbitmq.Product.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/16
 * TIME: 9:47
 */
public class ProductWorker {
    private static Logger logger = LoggerFactory.getLogger(ProductWorker.class);
    public static final String QUEUE_WORK = "WORK_QUEUE";

    public static void main(String[] args) {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2.配置工厂的RabbitMQ server的主机
        factory.setHost("localhost");
        Connection connection = null;
        Channel channel = null;
        try {
            //3.新建一个连接
            connection = factory.newConnection();
            //4.获取一个连接频道
            channel = connection.createChannel();
            /**
             * 5.配置一个队列的信息
             */
            channel.queueDeclare(QUEUE_WORK,true,false,false,null);
            for (int i = 0; i < 10; i++) {

                String message = getMessage()+"这是第"+i+"个！！！" ;
                /**
                 * 6.将消息放在队列中（字节数组形式）
                 * PERSISTENT_TEXT_PLAIN  Content-type "text/plain", deliveryMode 2 (persistent), priority zero
                 */
                channel.basicPublish("",QUEUE_WORK, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
                System.out.println("product Sent message is: '" + message + "'");
            }
        } catch (IOException e) {
            logger.error("rabbitMQ发送消息发生IO异常");
            e.printStackTrace();
        } catch (TimeoutException e) {
            logger.error("rabbitMQ发生连接超时异常");
            e.printStackTrace();
        }finally {
            try {
                //7.关闭连接
                channel.close();
                connection.close();
            } catch (IOException e) {
                logger.error("rabbitMQ关闭发生IO异常");
                e.printStackTrace();
            } catch (TimeoutException e) {
                logger.error("rabbitMQ发生关闭超时异常");
                e.printStackTrace();
            }
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

}
