package com.spring.sample.repository;

import com.spring.sample.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long>,
        CrudRepository<Product,Long> {

    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

    Product findById(int productId);
}
