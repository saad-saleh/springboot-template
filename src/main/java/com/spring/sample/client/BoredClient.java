package com.spring.sample.client;

import com.spring.sample.dto.BoredDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "sample-client", url = "https://www.boredapi.com/api/activity")
public interface BoredClient {

    @GetMapping
    BoredDto iamBored();

}
