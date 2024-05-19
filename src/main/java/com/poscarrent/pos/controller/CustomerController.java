package com.poscarrent.pos.controller;

import com.poscarrent.pos.dto.CustomerDto;
import com.poscarrent.pos.entity.Customer;
import com.poscarrent.pos.service.CustomerService;
import com.poscarrent.pos.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/customer")

public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ReportService reportService;

    @PostMapping("/save")
    public ResponseEntity<?> customerSave(@RequestBody CustomerDto customerDto){

        Customer customer = customerService.save(customerDto);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCustomer(){
        List<Customer> customers = customerService.getAll();

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id){
        Customer customer = customerService.getCustomerById(id);

        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, IOException {
        return reportService.exportReport(format);
    }



}
