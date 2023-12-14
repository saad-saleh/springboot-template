package com.spring.sample.controller;

import com.spring.sample.dto.EmailAddressDto;
import com.spring.sample.mapper.MapperHelper;
import com.spring.sample.model.EmailAddress;
import com.spring.sample.service.EmailAddressService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/email-address")
@OpenAPIDefinition(info = @Info(title = "Sample API", version = "1.0", description = "The API Description"))
@CrossOrigin(origins = {"*"}, exposedHeaders = {"X-Total-Count"})
public class EmailAddressController {

    @Autowired
    private MapperHelper<EmailAddressDto, EmailAddress> mapperHelperDtoToEntity;

    @Autowired
    private MapperHelper<EmailAddress, EmailAddressDto> mapperHelper;

    @Autowired
    private EmailAddressService emailAddressService;

    @GetMapping
    public Page<EmailAddress> getEmailAddresses(
            // Adjust specifications based on your needs
            Specification<EmailAddress> specification, Pageable pageable) {
        return emailAddressService.getEmailAddresses(specification, pageable);
    }

    @GetMapping("/{id}")
    public EmailAddress getEmailAddressById(@PathVariable("id") Integer emailAddressId) {
        return emailAddressService.getEmailAddress(emailAddressId);
    }

    @PostMapping
    public ResponseEntity<EmailAddress> createEmailAddress(
            @RequestBody EmailAddressDto emailAddressDto) {
        EmailAddress emailAddress = mapperHelperDtoToEntity.map(emailAddressDto, EmailAddress.class);
        EmailAddress response = emailAddressService.createEmailAddress(emailAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public EmailAddress editEmailAddress(@PathVariable("id") int emailAddressId,
                                         @RequestBody EmailAddressDto requestEmailAddress) {
        EmailAddress emailAddress = mapperHelperDtoToEntity.map(requestEmailAddress, EmailAddress.class);
        return emailAddressService.editEmailAddress(emailAddressId, emailAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmailAddress(@PathVariable("id") int emailAddressId) {
        emailAddressService.deleteEmailAddress(emailAddressId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
