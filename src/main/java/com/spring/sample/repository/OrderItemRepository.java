package com.spring.sample.repository;

import com.spring.sample.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem,Long>,
        CrudRepository<OrderItem,Long> {

    Page<OrderItem> findAll(Specification<OrderItem> specification, Pageable pageable);

    OrderItem findById(int orderItemId);
}
