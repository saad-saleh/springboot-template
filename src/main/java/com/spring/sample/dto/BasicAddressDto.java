package com.spring.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BasicAddressDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("street")
    private String street;

    @NotNull
    @JsonProperty("postal_code")
    private String postalCode;

}
