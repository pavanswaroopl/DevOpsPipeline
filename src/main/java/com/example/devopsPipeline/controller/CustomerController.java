package com.example.devopsPipeline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devopsPipeline.exception.UserAlreadyExistException;
import com.example.devopsPipeline.model.Customer;
import com.example.devopsPipeline.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/stackUnderflow")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;
    
   @PostMapping("/signup")
    public Boolean signUpUser(@RequestBody Customer customer) throws UserAlreadyExistException {
        try {
            log.info("add a user");
         
            customerService.signUpUser(customer);
        } catch (UserAlreadyExistException e) {
            return false;
        }
        return true;
    }


    @GetMapping("/users")
    public List<Customer> getCustomers() {
   
            log.info("getting all our users");
           return  customerService.getCustomers();
       

    } 

}
