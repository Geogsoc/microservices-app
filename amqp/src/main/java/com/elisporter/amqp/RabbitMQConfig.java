package com.elisporter.amqp;


import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//
//@AllArgsConstructor
//@Configuration
//public class RabbitMQConfig {
//
//
//    private final ConnectionFactory connectionFactory;
//    @Autowired
//    @Bean
//    public AmqpTemplate amqpTemplate() {
//
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//
//        rabbitTemplate.setMessageConverter(jacksonConverter());
//
//        return rabbitTemplate;
//    }
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
//
//        SimpleRabbitListenerContainerFactory factory =
//                new SimpleRabbitListenerContainerFactory();
//
//        factory.setConnectionFactory(connectionFactory);
//
//        factory.setMessageConverter(jacksonConverter());
//        return factory;
//    }
//
//    @Bean
//    public MessageConverter jacksonConverter() {
//        MessageConverter jackson2MessageConverter = new Jackson2JsonMessageConverter();
//
//        return jackson2MessageConverter;
//
//    }
//}

@Configuration
public class RabbitMQConfig {

    private final ConnectionFactory connectionFactory;

    // Constructor for dependency injection
    @Autowired
    public RabbitMQConfig(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonConverter());
        return factory;
    }

    @Bean
    public MessageConverter jacksonConverter() {


        return new Jackson2JsonMessageConverter();
    }
}