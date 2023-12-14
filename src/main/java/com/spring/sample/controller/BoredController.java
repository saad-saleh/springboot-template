package com.spring.sample.controller;
import com.spring.sample.client.BoredClient;
import com.spring.sample.dto.BoredDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/bored")
@CrossOrigin(origins = {"*"}, exposedHeaders = {"X-Total-Count"})
// @Api(value = "CatalogUploadTask APIs", tags = "CatalogUploadTask", produces = "application/json")
public class BoredController {

    @Autowired
    BoredClient client;

    @GetMapping
    public BoredDto iamBored() {
        return client.iamBored();
    }

}
