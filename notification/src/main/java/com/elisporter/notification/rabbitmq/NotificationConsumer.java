package com.elisporter.notification.rabbitmq;

import com.elisporter.clients.notification.NotificationRequest;
import com.elisporter.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {

        log.info("Sending notification request: {}", notificationRequest);

        notificationService.send(notificationRequest);

    }

}
