package com.grocer.configs.rabbit.exchanges;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CreateBindings {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection(RabbitConfiguration.AMQP_URL);
        try (Channel channel = connection.createChannel()) {
            //Create bindings - (queue, exchange, routingKey)
            channel.queueBind("Queue from CreateQueue class 1", "direct-exchange-from-Java", "binding1");
            channel.queueBind("Queue from CreateQueue class 2", "direct-exchange-from-Java", "binding");
            channel.queueBind("Queue from CreateQueue class 3", "direct-exchange-from-Java", "binding");
        }
        connection.close();
    }
}
