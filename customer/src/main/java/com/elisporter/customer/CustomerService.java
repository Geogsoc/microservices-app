package com.elisporter.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service //can also be @Component
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final RestTemplate restTemplate;

    private final CustomerRepository customerRepository;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());

        if (fraudCheckResponse.isFrauster()) {
            log.info("customer is fraudster: {}",customer.getId());
            throw new IllegalStateException("Is fraudster");
        }
        log.info("customer is not a fraudster: {}", customer.getId());
//todo: validation

    }
}
