package com.example.devopsPipeline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devopsPipeline.exception.UserAlreadyExistException;
import com.example.devopsPipeline.model.Customer;
import com.example.devopsPipeline.repository.CustomerRepository;

@Service
public class CustomerService {
 
    @Autowired
    CustomerRepository customerRepository;

    public void signUpUser(Customer customer) throws UserAlreadyExistException {

        int numOfCustomerExist = customerRepository.findByLoginId(customer.getLoginId());

        if (numOfCustomerExist > 0) {
            throw new UserAlreadyExistException("LoginId Already exist. change to new Login Id");
        }

         customerRepository.addCustomer(customer);

    }


    public  List<Customer>  getCustomers()  {

        List<Customer> customers = customerRepository.getAllCustomers();

        return customers;

    }



}
