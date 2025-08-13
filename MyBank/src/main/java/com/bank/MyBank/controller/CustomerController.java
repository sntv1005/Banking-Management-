package com.bank.MyBank.controller;

import com.bank.MyBank.entity.Customer;
import com.bank.MyBank.repository.CustomerRepo;
import com.bank.MyBank.request.CreateCustomerRequest;
import com.bank.MyBank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;



    @PostMapping("/create")
    public Customer create(@RequestBody CreateCustomerRequest req){
        return customerService.create(req);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        if (customerRepo.findByEmail(customer.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered!");
        }
        Customer saved = customerRepo.save(customer);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/getAll")
    public List<Customer> all(){
        return customerRepo.findAll();
    }

    @GetMapping("/{id}")
    public Customer one(@PathVariable Long id){
        return customerService.get(id);
    }
}
