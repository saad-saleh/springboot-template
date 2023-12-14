package com.spring.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.sample.model.PurchaseOrder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto extends BasicOrderItemDto{


    @ToString.Exclude
    @JsonProperty("purchase_order")
    private BasicPurchaseOrderDto purchaseOrder;

    @ToString.Exclude
    @JsonProperty("product")
    private BasicProductDto product;

}
