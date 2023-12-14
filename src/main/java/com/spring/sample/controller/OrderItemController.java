package com.spring.sample.controller;

import com.spring.sample.dto.OrderItemDto;
import com.spring.sample.mapper.MapperHelper;
import com.spring.sample.model.OrderItem;
import com.spring.sample.service.OrderItemService;
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
@RequestMapping("/order-item")
@OpenAPIDefinition(info = @Info(title = "Sample API", version = "1.0", description = "The API Description"))
@CrossOrigin(origins = {"*"}, exposedHeaders = {"X-Total-Count"})
public class OrderItemController {

    @Autowired
    private MapperHelper<OrderItemDto, OrderItem> mapperHelperDtoToEntity;

    @Autowired
    private MapperHelper<OrderItem, OrderItemDto> mapperHelper;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public Page<OrderItem> getOrderItems(
            @Join(path = "product", alias = "product", distinct = true)
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "quantity", params = "quantity", spec = Equal.class),
                    // Add more specifications as needed
            })
            Specification<OrderItem> specification, Pageable pageable) {
        return orderItemService.getOrderItems(specification, pageable);
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable("id") Integer orderItemId) {
        return orderItemService.getOrderItem(orderItemId);
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(
            @RequestBody OrderItemDto orderItemDto) {
        OrderItem orderItem = mapperHelperDtoToEntity.map(orderItemDto, OrderItem.class);
        OrderItem response = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public OrderItem editOrderItem(@PathVariable("id") int orderItemId,
                                   @RequestBody OrderItemDto requestOrderItem) {
        OrderItem orderItem = mapperHelperDtoToEntity.map(requestOrderItem, OrderItem.class);
        return orderItemService.editOrderItem(orderItemId, orderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrderItem(@PathVariable("id") int orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
