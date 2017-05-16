package com.eumji.rabbitmq;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者 --消息的接受者
 * FILE: com.eumji.rabbitmq.CustomerWorker.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/16
 * TIME: 10:02
 */
public class CustomerWorker extends Thread {
    private static Logger logger = LoggerFactory.getLogger(CustomerWorker.class);
    public static final String QUEUE_WORK = "WORK_QUEUE";
    private String workName;

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    @Override
    public void run() {
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
             */
            channel.queueDeclare(QUEUE_WORK,true,false,false,null);
            System.out.println(workName +" wait  message！！！");
            //
            channel.basicQos(1);
            final Channel finalChannel = channel;
            Consumer consumer = new DefaultConsumer(finalChannel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, 0, body.length, "UTF-8");
                    System.out.println(workName+" "+Thread.currentThread().getName()+" get message："+message);
                    try {
                        Thread.sleep(800);
                        //消息处理完成确认
                        finalChannel.basicAck(envelope.getDeliveryTag(),false);
                        System.out.println(workName+" "+Thread.currentThread().getName()+"处理消息完毕");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            //消息消费完成确认
            channel.basicConsume(QUEUE_WORK,false,consumer);

        } catch (IOException e) {
            logger.error("rabbitMQ发送消息发生IO异常");
            e.printStackTrace();
        } catch (TimeoutException e) {
            logger.error("rabbitMQ发生连接超时异常");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        CustomerWorker worker = new CustomerWorker();
        worker.setWorkName("customer");
        worker.start();
        CustomerWorker worker2 = new CustomerWorker();
        worker2.setWorkName("customer2");
        worker2.start();

    }
}
