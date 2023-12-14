package com.spring.sample.controller;

import com.spring.sample.dto.CustomerDto;
import com.spring.sample.mapper.MapperHelper;
import com.spring.sample.model.Customer;
import com.spring.sample.service.CustomerService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/customer")
@OpenAPIDefinition(info = @Info(title = "Sample API", version = "1.0", description = "The API Description"))
@CrossOrigin(origins = {"*"}, exposedHeaders = {"X-Total-Count"})
public class CustomerController {

    @Autowired
    private MapperHelper<CustomerDto, Customer> mapperHelperDtoToEntity;

    @Autowired
    private MapperHelper<Customer, CustomerDto> mapperHelper;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Page<Customer> getCustomers(
            @Join(path = "addressList", alias = "address", distinct = true)
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = Equal.class),
                    @Spec(path = "address.street", params = "street", spec = Equal.class)
            })
            Specification<Customer> specification, Pageable pageable) {
        return customerService.getCustomers(specification, pageable);
    }


    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestBody CustomerDto customerDto) {
        Customer customer = mapperHelperDtoToEntity.map(customerDto, Customer.class);
        Customer response = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public Customer editCustomer(@PathVariable("id") int customerId,
                                 @RequestBody CustomerDto requestCustomer) {
        Customer customer = mapperHelperDtoToEntity.map(requestCustomer, Customer.class);
        return customerService.editCustomer(customerId, customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") int customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
