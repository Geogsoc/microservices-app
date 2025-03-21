package com.elisporter.notification;

import com.elisporter.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.elisporter.notification",
                "com.elisporter.amqp",
        }
)

@PropertySource("classpath:clients-${spring.profiles.active}.properties")
//@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig notificationConfig) {

        return args -> {
            producer.publish(new Person("elis",88),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKeys());
        };


    }

    record Person(String name, Integer age) {
    }
}
