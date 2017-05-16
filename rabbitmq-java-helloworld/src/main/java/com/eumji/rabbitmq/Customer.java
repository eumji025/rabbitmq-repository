package com.eumji.rabbitmq;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者 --消息的接受者
 * FILE: com.eumji.rabbitmq.Customer.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/16
 * TIME: 10:02
 */
public class Customer {
    private static Logger logger = LoggerFactory.getLogger(Product.class);
    public static final String QUEUE_HELLO = "FIRST_QUEUE";

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
             * 5.声明队列的信息
             * 在RabbitMQ中，队列声明是幂等性的（一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同）
             * 也就是说，如果不存在，就创建，如果存在，不会对已经存在的队列产生任何影响。
             * @param queue 队列名称
             * @param durable 如果我们声明持久队列（队列将在服务器重新启动后生效），则为true
             * @param exclusive 如果我们声明排他队列（限于此连接），则为true
             * @param autoDelete 如果我们声明一个自动删除队列，则为true（服务器将在不再使用时将其删除）
             * @param arguments 队列的其他属性（构造参数）
             */
            channel.queueDeclare(QUEUE_HELLO,false,false,false,null);
            System.out.println("customer wait  message！！！");
            //覆盖DefaultConsumer的handleDelivery方法，回调信息
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body,0,body.length,"UTF-8");
                    System.out.println(message);
                    logger.info("收到productor的消息："+message);
                }
            };
            channel.basicConsume(QUEUE_HELLO,true,consumer);
        } catch (IOException e) {
            logger.error("rabbitMQ发送消息发生IO异常");
            e.printStackTrace();
        } catch (TimeoutException e) {
            logger.error("rabbitMQ发生连接超时异常");
            e.printStackTrace();
        }
    }
}
