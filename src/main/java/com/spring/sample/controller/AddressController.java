package com.spring.sample.controller;

import com.spring.sample.dto.AddressDto;
import com.spring.sample.mapper.MapperHelper;
import com.spring.sample.model.Address;
import com.spring.sample.service.AddressService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/address")
@OpenAPIDefinition(info = @Info(title = "Sample API", version = "1.0", description = "The API Description"))
@CrossOrigin(origins = {"*"}, exposedHeaders = {"X-Total-Count"})
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    @Autowired
    private MapperHelper<AddressDto, Address> mapperHelperDtoToEntity;

    @Autowired
    private MapperHelper<Address, AddressDto> mapperHelper;


    @Autowired
    AddressService addressService;

    @GetMapping
    public Page<Address> getAddressList(
            @Join(path = "customer", alias = "customer")
            @And({
                    @Spec(path = "customer.id", params = "customer_id", spec = Equal.class),
                    @Spec(path = "postalCode", params = "postal_code", spec = Equal.class),
                    @Spec(path = "street", params = "street", spec = Equal.class)
            })
            Specification<Address> specification, Pageable pageable) {
        return addressService.getAddresses(specification,pageable);
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable("id") Integer addressId) {
        logger.debug("AddressController>>getAddressById>>addressId: {}", addressId.toString());
        Address address = addressService.getAddress(addressId);
        return address;
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(
            @RequestBody AddressDto addressDto) {
        logger.debug("createAddress>>request address:{}",addressDto);

        Address address = mapperHelperDtoToEntity.map(addressDto, Address.class);
        Address response = addressService.createAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public Address editAddress(@PathVariable("id") int addressId,
                               @Valid @RequestBody AddressDto requestAddress){
        Address address = mapperHelperDtoToEntity.map(requestAddress, Address.class);
        Address response = addressService.editAddress(addressId,address);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress(@PathVariable("id") int addressId){
        addressService.deleteAddress(addressId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
