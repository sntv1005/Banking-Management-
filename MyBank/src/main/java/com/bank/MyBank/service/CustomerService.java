package com.bank.MyBank.service;

import com.bank.MyBank.entity.Customer;
import com.bank.MyBank.exception.NotFoundException;
import com.bank.MyBank.repository.CustomerRepo;
import com.bank.MyBank.request.CreateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;


    public Customer create(CreateCustomerRequest req) {
        Customer c = new Customer();
        c.setCustomerId("CUS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        c.setName(req.getName());
        c.setDateOfBirth(LocalDate.parse(req.getDateOfBirth()));
        c.setGender(req.getGender());
        c.setEmail(req.getEmail());
        c.setPhone(req.getPhone());
        c.setAddressLine(req.getAddressLine());
        c.setCity(req.getCity());
        c.setState(req.getState());
        c.setPostalCode(req.getPostalCode());
        c.setCountry(req.getCountry());
        c.setGovernmentIdType(req.getGovernmentIdType());
        c.setGovernmentIdNumber(req.getGovernmentIdNumber());
        c.setPassword(req.getPassword());
        c.setMonthlyIncome(req.getMonthlyIncome());
        c.setOccupation(req.getOccupation());
        return customerRepo.save(c);
    }


    public Customer get(Long id){
        return customerRepo.findById(id).orElseThrow(() -> new NotFoundException("Customer not found: " + id));
    }
}
