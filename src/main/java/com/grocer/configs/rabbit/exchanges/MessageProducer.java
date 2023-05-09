package com.grocer.configs.rabbit.exchanges;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class MessageProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection(RabbitConfiguration.AMQP_URL);
        Channel channel = connection.createChannel();

       String message = "Turn from Producer to third queue";
       channel.basicPublish("direct-exchange-from-Java","binding",null,message.getBytes());
        channel.close();
        connection.close();
    }
}
