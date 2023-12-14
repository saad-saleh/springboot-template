package com.spring.sample.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BoredDto {

    @JsonProperty("activity")
    String activity;

    @JsonProperty("type")
    String type;

    @JsonProperty("participants")
    String Participants;

    @JsonProperty("price")
    String price;

    @JsonProperty("link")
    String link;

    @JsonProperty("key")
    String key;

    @JsonProperty("accessibility")
    String accessibility;
}
