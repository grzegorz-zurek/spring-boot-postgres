package com.example.demo.services.implementations;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomersRepository;
import com.example.demo.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomersRepository repository;
    @Autowired
    public CustomerServiceImpl(CustomersRepository customersRepository) {
        this.repository = customersRepository;
    }

    @Override
    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Customer customer = repository.findById(id).orElse(null);
        if(customer == null) return null;
        repository.deleteById(id);
        return customer;
    }

    @Override
    public Customer updateCustomer(Long id, String firstName, String lastName, String emailAddress) {
        if(id == null) return null;
        Customer customer = repository.findById(id).orElse(null);
        if(customer == null) return null;
        if(firstName != null) {
            customer.setFirst_name(firstName);
        }
        if(lastName != null) {
            customer.setLast_name(lastName);
        }
        if(emailAddress != null) {
            customer.setEmail_address(emailAddress);
        }
        return repository.save(customer);
    }


}
