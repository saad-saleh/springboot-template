package com.spring.sample.repository;

import com.spring.sample.model.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends PagingAndSortingRepository<PurchaseOrder,Long>, CrudRepository<PurchaseOrder,Long> {


    Page<PurchaseOrder> findAll(Specification<PurchaseOrder> specification, Pageable pageable);

    PurchaseOrder findById(int addressId);

}
