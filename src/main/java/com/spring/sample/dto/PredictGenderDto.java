package com.spring.sample.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class PredictGenderDto {

    @JsonProperty("count")
    String count;

    @NotNull
    @JsonProperty("name")
    String name;

    @JsonProperty("gender")
    String gender;

    @JsonProperty("probability")
    String probability;

}
