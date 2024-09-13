package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonAlias("customerID")
    private Long id;
    @Column(nullable = false)
    @JsonAlias("firstName")
    @NonNull
    private String first_name;
    @JsonAlias("lastName")
    @Column(nullable = false)
    @NonNull
    private String last_name;
    @JsonAlias("emailAddress")
    @Column(nullable = false)
    @NonNull
    private String email_address;

    public Customer() {}

    public Customer(String first_name, String last_name, String email_address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_address = email_address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(first_name, customer.first_name) && Objects.equals(last_name, customer.last_name) && Objects.equals(email_address, customer.email_address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, email_address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email_address='" + email_address + '\'' +
                '}';
    }
}
