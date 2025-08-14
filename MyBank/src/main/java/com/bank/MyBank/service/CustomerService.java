package com.bank.MyBank.service;

import com.bank.MyBank.entity.Customer;
import com.bank.MyBank.exception.NotFoundException;
import com.bank.MyBank.repository.CustomerRepo;
import com.bank.MyBank.request.CreateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;


    public Customer create(CreateCustomerRequest req){
        Customer c = new Customer();
        c.setCustomerId("CUS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        c.setName(req.name); c.setEmail(req.email); c.setPhone(req.phone);
        c.setMonthlyIncome(req.monthlyIncome);
        return customerRepo.save(c);
    }

    public Customer get(Long id){
        return customerRepo.findById(id).orElseThrow(() -> new NotFoundException("Customer not found: " + id));
    }
}
