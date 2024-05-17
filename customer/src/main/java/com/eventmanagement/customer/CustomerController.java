package com.eventmanagement.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerServiceImpl customerServiceImpl) {

    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        Customer newCustomer = customerServiceImpl.registerCustomer(customerRegistrationRequest);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> get() {
        List<Customer> customers = customerServiceImpl.get();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> get(@PathVariable("customerId") Integer customerId) {
        Customer customer = customerServiceImpl.get(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        Customer updateCustomer = customerServiceImpl.update(customer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> delete(@PathVariable("customerId") int customerId) {
        customerServiceImpl.delete(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}