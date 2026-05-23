package com.example.customerapi.controller;

import com.example.customerapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerRestController extends CustomerRestControllerV1 {

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        super(customerService);
    }
}
