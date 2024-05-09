package com.eventmanagement.customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record CustomerServiceImpl(CustomerRepository customerRepository) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .tier(1)
                .build();
        customerRepository.save(customer);
    }

    public List<Customer> get() {
        return customerRepository.findAll();
    }

    public Optional<Customer> get(int customerId) {
        return customerRepository.findById(customerId);
    }

    public void promote(int customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        customer.setTier(2);
        customerRepository.save(customer);
    }

    public void demote(int customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        customer.setTier(1);
        customerRepository.save(customer);
    }
}
