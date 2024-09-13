package com.example.demo.services.interfaces;

import com.example.demo.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    Customer getCustomerById(Long id);

    Customer addCustomer(Customer customer);

    Customer deleteCustomer(Long id);

    Customer updateCustomer(Long id, String firstName, String lastName, String emailAddress);
}
