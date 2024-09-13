package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    @Autowired
    CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(service.getCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long id) {
        Customer customer = service.getCustomerById(id);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Validated @RequestBody Customer customer) {
        Customer result = service.addCustomer(customer);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.status(400).body(null);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer customer1 = service.updateCustomer(customer.getId(), customer.getFirst_name(), customer.getLast_name(), customer.getEmail_address());
        return customer1 != null ? ResponseEntity.ok(customer1) : ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") Long id) {
        Customer customer = service.deleteCustomer(id);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.status(400).body(null);
    }
}
