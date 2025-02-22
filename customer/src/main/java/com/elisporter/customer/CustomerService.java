package com.elisporter.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service //can also be @Component
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

Customer customer = Customer.builder()
        .firstName(customerRegistrationRequest.firstName())
        .lastName(customerRegistrationRequest.lastName())
        .email(customerRegistrationRequest.email())
        .build();


customerRepository.save(customer);

//todo: validation

    }
}
