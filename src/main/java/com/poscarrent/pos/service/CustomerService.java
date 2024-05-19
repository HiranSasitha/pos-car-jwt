package com.poscarrent.pos.service;

import com.poscarrent.pos.dto.CustomerDto;
import com.poscarrent.pos.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    Customer save(CustomerDto customerDto);
    List<Customer> getAll();

    Customer getCustomerById(Long id);
}
