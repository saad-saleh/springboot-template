package com.spring.sample.repository;

import com.spring.sample.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long>,
        CrudRepository<Customer,Long> {

    Page<Customer> findAll(Specification<Customer> specification, Pageable pageable);

    Customer findById(int customerId);
}
