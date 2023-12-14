package com.spring.sample.service;

import com.spring.sample.exception.CustomException;
import com.spring.sample.exception.ErrorCode;
import com.spring.sample.model.PurchaseOrder;
import com.spring.sample.repository.PurchaseOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseOrderService {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderService.class);

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public Page<PurchaseOrder> getPurchaseOrders(Specification<PurchaseOrder> specification, Pageable pageable) {
        return purchaseOrderRepository.findAll(specification, pageable);
    }

    public PurchaseOrder getPurchaseOrder(Long purchaseOrderId) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId);
        if (!purchaseOrder.isPresent() || purchaseOrder == null) {
            throw new CustomException("PurchaseOrder not found", ErrorCode.NOT_FOUND);
        }
        return purchaseOrder.get();
    }

    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrder createdPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        return createdPurchaseOrder;
    }

    public PurchaseOrder editPurchaseOrder(Long purchaseOrderId, PurchaseOrder purchaseOrder) {
        logger.info("editPurchaseOrder>>purchaseOrderId:{}>>requestPurchaseOrder:{}", purchaseOrderId, purchaseOrder);

        PurchaseOrder retrievedPurchaseOrder = getPurchaseOrder(purchaseOrderId);
        // Set other properties as needed
        purchaseOrderRepository.save(retrievedPurchaseOrder);
        return retrievedPurchaseOrder;
    }

    public void deletePurchaseOrder(Long purchaseOrderId) {
        purchaseOrderRepository.delete(getPurchaseOrder(purchaseOrderId));
    }
}
