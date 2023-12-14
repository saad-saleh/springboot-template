package com.spring.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto extends  BasicCustomerDto{

    @ToString.Exclude
    @JsonProperty("addresses")
    private List<BasicAddressDto> addresses;

    @ToString.Exclude
    @JsonProperty("email_addresses")
    private List<BasicEmailAddressDto> emailAddresses;

    @ToString.Exclude
    @JsonProperty("purchase_orders")
    private List<BasicPurchaseOrderDto> purchaseOrders;

}
