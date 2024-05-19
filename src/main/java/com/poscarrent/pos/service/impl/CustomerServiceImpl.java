package com.poscarrent.pos.service.impl;

import com.poscarrent.pos.dto.CustomerDto;
import com.poscarrent.pos.entity.Customer;
import com.poscarrent.pos.repo.CustomerRepo;
import com.poscarrent.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public Customer save(CustomerDto customerDto) {

        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());

        customerRepo.save(customer);

        return customer;
    }

    @Override
    public List<Customer> getAll() {

        List<Customer> customers = new ArrayList<>();

        customers = customerRepo.findAll();
        return customers;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = customerRepo.findById(id).get();

        return customer;
    }
}
