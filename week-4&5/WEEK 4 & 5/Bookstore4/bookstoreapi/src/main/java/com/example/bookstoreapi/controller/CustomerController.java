package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    // POST to create a new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customer.setId((long) (customers.size() + 1)); // Simple ID generation logic
        customers.add(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    // POST to register a new customer using form data
@PostMapping("/register")
public ResponseEntity<Customer> registerCustomer(@ModelAttribute Customer customer) {
    customer.setId((long) (customers.size() + 1)); // Simple ID generation logic
    customers.add(customer);
    return new ResponseEntity<>(customer, HttpStatus.CREATED);
}

}
