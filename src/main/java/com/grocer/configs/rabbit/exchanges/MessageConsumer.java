package com.grocer.configs.rabbit.exchanges;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
@Component
public class MessageConsumer {
        private CountDownLatch latch = new CountDownLatch(1);

        public void receiveMessage(String message) {
            System.out.println("Received <" + message + ">");
            latch.countDown();
        }

        public CountDownLatch getLatch() {
            return latch;
        }
}
