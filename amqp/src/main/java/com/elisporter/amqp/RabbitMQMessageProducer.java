package com.elisporter.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Slf4j
//@Component
//@AllArgsConstructor
//public class RabbitMQMessageProducer {
//
//    private final AmqpTemplate amqpTemplate;
//    private static final Logger log = LoggerFactory.getLogger(RabbitMQMessageProducer.class);
//    public void publish(Object payload, String exchange, String routingKey) {
//
//        log.info("publishing to: {} using routing key: {} payload:{}", exchange, routingKey, payload);
//
//        amqpTemplate.convertAndSend(exchange, routingKey, payload);
//
//        log.info("published to: {} using routing key: {} payload:{}", exchange, routingKey, payload);
//    }
//}


@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;
 //   private static final Logger log = LoggerFactory.getLogger(RabbitMQMessageProducer.class);

    // Constructor-based dependency injection
//    @Autowired
//    public RabbitMQMessageProducer(AmqpTemplate amqpTemplate) {
//        this.amqpTemplate = amqpTemplate;
//    }

    public void publish(Object payload, String exchange, String routingKey) {

        log.info("publishing to: {} using routing key: {} payload:{}", exchange, routingKey, payload);

        amqpTemplate.convertAndSend(exchange, routingKey, payload);

        log.info("published to: {} using routing key: {} payload:{}", exchange, routingKey, payload);
    }
}