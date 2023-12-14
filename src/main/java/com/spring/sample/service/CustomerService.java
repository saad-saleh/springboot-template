package com.spring.sample.service;

import com.spring.sample.exception.CustomException;
import com.spring.sample.exception.ErrorCode;
import com.spring.sample.model.Customer;
import com.spring.sample.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public Page<Customer> getCustomers(Specification<Customer> specification, Pageable pageable) {
        return customerRepository.findAll(specification, pageable);
    }

    public Customer getCustomer(int customerId) {
        Customer customer = customerRepository.findById(customerId);
        if (customer == null) {
            throw new CustomException("Customer not found", ErrorCode.NOT_FOUND);
        }
        return customer;
    }

    public Customer createCustomer(Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer;
    }

    public Customer editCustomer(int customerId, Customer customer) {
        logger.info("editCustomer>>customerId:{}>>requestCustomer:{}", customerId, customer);

        Customer retrievedCustomer = getCustomer(customerId);
        // Set other properties as needed
        customerRepository.save(retrievedCustomer);
        return retrievedCustomer;
    }

    public void deleteCustomer(int customerId) {
        Customer customer = getCustomer(customerId);
        customerRepository.delete(customer);
    }
}
