package com.grocer.configs.rabbit.exchanges;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CreateQueue {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection(RabbitConfiguration.AMQP_URL);
        Channel channel = connection.createChannel();

        //Create the Queues
        channel.queueDeclare("Queue from CreateQueue class 1", true, false, false, null);
        channel.queueDeclare("Queue from CreateQueue class 2", true, false, false, null);
        channel.queueDeclare("Queue from CreateQueue class 3", true, false, false, null);

        channel.close();
        connection.close();
    }
}
