package com.elisporter.customer;

import org.springframework.stereotype.Service;

@Service //can also be @Component
public record CustomerService(CustomerRepository customerRepository) {
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
