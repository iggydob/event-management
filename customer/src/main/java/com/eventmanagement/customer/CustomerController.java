package com.eventmanagement.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerServiceImpl customerServiceImpl) {

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        customerServiceImpl.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping
    public List<Customer> get() {
        return customerServiceImpl.get();
    }

    @GetMapping("/{customerId}")
    public Optional<Customer> get(@PathVariable Integer customerId) {
        return customerServiceImpl.get(customerId);
    }

    @PutMapping("/{customerId}/{commandLine}")
    public void update(@PathVariable Integer customerId,
                       @PathVariable String commandLine) {

        switch (commandLine) {
            case "promote":
                customerServiceImpl.promote(customerId);
                break;
            case "demote":
                customerServiceImpl.demote(customerId);
                break;
            default:
                break;
        }
    }
}