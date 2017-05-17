package com.eumji.rabbit;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * FILE: com.eumji.rabbit.ReceiveWorker.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/16
 * TIME: 21:30
 */
public class TopicReceive extends Thread{
    private static Logger logger = LoggerFactory.getLogger(TopicReceive.class);
    private static final String EXCHANGE_NAME = "topic_worker";
    private String workerName;
    private String topicInfo;
    public TopicReceive(String workerName,String topicInfo){
        this.topicInfo = topicInfo;
        this.workerName = workerName;
    }
    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection;
        Channel channel;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, topicInfo);
            System.out.println("queue name is :"+queue);
            System.out.println(workerName+" worker wait  message!!!");
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                    String message = new String(body, 0, body.length, "UTF-8");
                    System.out.println(workerName+" receive "+envelope.getRoutingKey()+" message ：" + message);
                }
            };
            channel.basicConsume(queue, true, consumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        TopicReceive info = new TopicReceive("info_topic","*.info.*");
        info.start();
        TopicReceive rabbit = new TopicReceive("rabbit_topic", "*.*.rabbit");
        rabbit.start();
        TopicReceive animal = new TopicReceive("animal_topic", "animal.*.*");
        animal.start();


    }
}
