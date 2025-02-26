package com.elisporter.customer;

import com.elisporter.amqp.RabbitMQMessageProducer;
import com.elisporter.clients.fraud.FraudCheckResponse;
import com.elisporter.clients.fraud.FraudClient;
import com.elisporter.clients.notification.NotificationClient;
import com.elisporter.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service //can also be @Component
@AllArgsConstructor
@Slf4j
public class CustomerService {


    private final CustomerRepository customerRepository;

    private final FraudClient fraudClient;

    private RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);

        // removes need for restTemplate.getForObject("path")
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            log.info("customer is fraudster: {}", customer.getId());
            throw new IllegalStateException("Is fraudster " + customer.getFirstName() + " " + customer.getLastName());
        }
        log.info("customer is not a fraudster: {}", customer.getId());

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to the service...",
                        customer.getFirstName()));

//        notificationClient.sendNotification(notificationRequest);

        rabbitMQMessageProducer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");

    }
}
