package com.spring.sample.controller;

import com.spring.sample.client.GenderPredictorClient;
import com.spring.sample.dto.PredictGenderDto;
import com.spring.sample.exception.CustomException;
import com.spring.sample.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gender")
@CrossOrigin(origins = {"*"}, exposedHeaders = {"X-Total-Count"})
// @Api(value = "CatalogUploadTask APIs", tags = "CatalogUploadTask", produces = "application/json")
public class GenderPredictorController {

    @Autowired
    GenderPredictorClient genderPredictorClient;

    @GetMapping
    public PredictGenderDto predictGender(
            @RequestParam(value = "name",required = true ) String namex) throws Exception {
        throw new CustomException("Custom Message", ErrorCode.INVALID_INPUT);
        // return genderPredictorClient.predictGenderApi(name);
    }

}
