package com.spring.sample.service;

import com.spring.sample.exception.CustomException;
import com.spring.sample.exception.ErrorCode;
import com.spring.sample.model.OrderItem;
import com.spring.sample.repository.OrderItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private static final Logger logger = LoggerFactory.getLogger(OrderItemService.class);

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Page<OrderItem> getOrderItems(Specification<OrderItem> specification, Pageable pageable) {
        return orderItemRepository.findAll(specification, pageable);
    }

    public OrderItem getOrderItem(int orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId);
        if (orderItem == null) {
            throw new CustomException("OrderItem not found", ErrorCode.NOT_FOUND);
        }
        return orderItem;
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        OrderItem createdOrderItem = orderItemRepository.save(orderItem);
        return createdOrderItem;
    }

    public OrderItem editOrderItem(int orderItemId, OrderItem orderItem) {
        logger.info("editOrderItem>>orderItemId:{}>>requestOrderItem:{}", orderItemId, orderItem);

        OrderItem retrievedOrderItem = getOrderItem(orderItemId);
        // Set other properties as needed
        orderItemRepository.save(retrievedOrderItem);
        return retrievedOrderItem;
    }

    public void deleteOrderItem(int orderItemId) {
        OrderItem orderItem = getOrderItem(orderItemId);
        orderItemRepository.delete(orderItem);
    }
}
