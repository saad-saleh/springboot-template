package com.spring.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicEmailAddressDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("address")
    private String address;
}
