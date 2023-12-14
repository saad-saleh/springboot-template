package com.spring.sample.controller;

import com.spring.sample.dto.PurchaseOrderDto;
import com.spring.sample.mapper.MapperHelper;
import com.spring.sample.model.PurchaseOrder;
import com.spring.sample.service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
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
@RequestMapping("/purchase-order")
@OpenAPIDefinition(info = @Info(title = "Sample API", version = "1.0", description = "The API Description"))
@CrossOrigin(origins = {"*"}, exposedHeaders = {"X-Total-Count"})
public class PurchaseOrderController {

    @Autowired
    private MapperHelper<PurchaseOrderDto, PurchaseOrder> mapperHelperDtoToEntity;

    @Autowired
    private MapperHelper<PurchaseOrder, PurchaseOrderDto> mapperHelper;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public Page<PurchaseOrder> getPurchaseOrders(
            @Join(path = "customer", alias = "customer", distinct = true)
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "orderDate", params = "order_date", spec = Equal.class),
                    @Spec(path = "customer.id", params = "customer_id", spec = Equal.class),
                    @Spec(path = "customer.name", params = "customer_name", spec = Like.class),
                    // Add more specifications as needed
            })
            Specification<PurchaseOrder> specification, Pageable pageable) {
        return purchaseOrderService.getPurchaseOrders(specification, pageable);
    }

    @GetMapping("/{id}")
    public PurchaseOrder getPurchaseOrderById(@PathVariable("id") Long purchaseOrderId) {
        return purchaseOrderService.getPurchaseOrder(purchaseOrderId);
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(
            @RequestBody PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = mapperHelperDtoToEntity.map(purchaseOrderDto, PurchaseOrder.class);
        PurchaseOrder response = purchaseOrderService.createPurchaseOrder(purchaseOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public PurchaseOrder editPurchaseOrder(@PathVariable("id") Long purchaseOrderId,
                                           @RequestBody PurchaseOrderDto requestPurchaseOrder) {
        PurchaseOrder purchaseOrder = mapperHelperDtoToEntity.map(requestPurchaseOrder, PurchaseOrder.class);
        return purchaseOrderService.editPurchaseOrder(purchaseOrderId, purchaseOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePurchaseOrder(@PathVariable("id") Long purchaseOrderId) {
        purchaseOrderService.deletePurchaseOrder(purchaseOrderId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
