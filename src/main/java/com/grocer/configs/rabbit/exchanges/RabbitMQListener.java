package com.grocer.configs.rabbit.exchanges;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMQListener {
    Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);
@RabbitListener(queues = "Queue from CreateQueue class 1")
    public void processQueue(String msg) {
        logger.info("Receives from queue {}",msg);
    }
}
