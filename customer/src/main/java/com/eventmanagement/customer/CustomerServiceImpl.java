package com.eventmanagement.customer;

import com.eventmanagement.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record CustomerServiceImpl(CustomerRepository customerRepository) {

    public Customer registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .tier(1)
                .imageUrl("https://www.writeups.org/wp-content/uploads/Harry-Potter-Philosopher-Stone-era.jpg")
                .build();
        customerRepository.save(customer);
        return customer;
    }

    public List<Customer> get() {
        return customerRepository.findAll();
    }

    public Customer get(Integer customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer #" + customerId + "was not found."));
    }

    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(int customerId) {
        customerRepository.deleteById(customerId);
    }
}
