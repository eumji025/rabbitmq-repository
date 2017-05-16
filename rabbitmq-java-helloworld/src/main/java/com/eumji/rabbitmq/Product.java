package com.eumji.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
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
public class Product {
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
             * 5.配置一个队列的信息
             * 在RabbitMQ中，队列声明是幂等性的（一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同）
             * 也就是说，如果不存在，就创建，如果存在，不会对已经存在的队列产生任何影响。
             * @param queue 队列名称
             * @param durable 如果我们声明持久队列（队列将在服务器重新启动后生效），则为true
             * @param exclusive 如果我们声明排他队列（限于此连接），则为true
             * @param autoDelete 如果我们声明一个自动删除队列，则为true（服务器将在不再使用时将其删除）
             * @param arguments 队列的其他属性（构造参数）
             */

            channel.queueDeclare(QUEUE_HELLO,false,false,false,null);
            String message = "Hello World！" ;
            /**
             * 6.将消息放在队列中（字节数组形式）
             * @param exchange 交换发布消息
             * @param routingKey 路由密钥
             * @param props 消息的其他属性 - 路由头等
             * @param body 消息体
             */
            channel.basicPublish("",QUEUE_HELLO,null,message.getBytes());
            System.out.println("product Sent '" + message + "'");
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
}
