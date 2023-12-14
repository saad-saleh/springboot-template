package com.spring.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasicPurchaseOrderDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("order_date")
    private Date orderDate;


}
