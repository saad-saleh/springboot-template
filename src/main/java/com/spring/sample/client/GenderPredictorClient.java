package com.spring.sample.client;

import com.spring.sample.dto.PredictGenderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gender-predictor-client", url = "https://api.genderize.io")
public interface GenderPredictorClient {

    @GetMapping
    PredictGenderDto predictGenderApi(@RequestParam("name") String inputName);
}
