package com.spring.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDto extends BasicPurchaseOrderDto{

    @ToString.Exclude
    @JsonProperty("customer")
    private BasicCustomerDto customer;

    @ToString.Exclude
    @JsonProperty("order_items")
    private List<BasicOrderItemDto> orderItems;

}
