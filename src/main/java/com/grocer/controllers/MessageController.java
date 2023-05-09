package com.grocer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageController {
Logger logger = LoggerFactory.getLogger(MessageController.class);
private final AmqpTemplate amqpTemplate;

    public MessageController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @PostMapping("/msg")
    public ResponseEntity<String> msg(@RequestBody String msg) {
        logger.info("Start Logging");
        amqpTemplate.convertAndSend("Queue",msg);
        return ResponseEntity.ok("Ok");
    }
}