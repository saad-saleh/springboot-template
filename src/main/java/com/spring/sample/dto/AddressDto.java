package com.spring.sample.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto extends BasicAddressDto{

    @ToString.Exclude
    private BasicCustomerDto customer;
}
