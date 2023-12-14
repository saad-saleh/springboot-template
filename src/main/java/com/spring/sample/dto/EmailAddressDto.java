package com.spring.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailAddressDto extends BasicEmailAddressDto{

    @ToString.Exclude
    @JsonProperty("customer")
    private BasicCustomerDto customer;

}
