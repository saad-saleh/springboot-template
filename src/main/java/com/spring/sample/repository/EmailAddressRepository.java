package com.spring.sample.repository;

import com.spring.sample.model.EmailAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAddressRepository extends PagingAndSortingRepository<EmailAddress,Long>,
        CrudRepository<EmailAddress,Long> {


    Page<EmailAddress> findAll(Specification<EmailAddress> specification, Pageable pageable);

    EmailAddress findById(int emailAddressId);
}
