package com.spring.sample.service;

import com.spring.sample.exception.CustomException;
import com.spring.sample.exception.ErrorCode;
import com.spring.sample.model.Address;
import com.spring.sample.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);


    @Autowired
    AddressRepository addressRepository;

    public Page<Address> getAddresses(Specification<Address> specification, Pageable pageable){
        return addressRepository.findAll(specification,pageable);
    }

    public Address getAddress(int addressId){
        Address address = addressRepository.findById(addressId);
        if (address == null)
                throw new CustomException("Address not found", ErrorCode.NOT_FOUND);
        return address;
    }

    public Address createAddress(Address address) {
        Address createdAddress = addressRepository.save(address);
        return createdAddress;
    }

    public Address editAddress(int addressId, Address address) {
        logger.info("editAddress>>addressId:{}>>requestAddress:{}",addressId,address);

        Address retrievedAddress = getAddress(addressId);
        retrievedAddress.setCustomer(address.getCustomer());
        retrievedAddress.setId(address.getId());
        retrievedAddress.setStreet(address.getStreet());
        retrievedAddress.setPostalCode(address.getPostalCode());
        addressRepository.save(retrievedAddress);
        return retrievedAddress;
    }

    public void deleteAddress(int addressId) {
        Address address = getAddress(addressId);
        addressRepository.delete(address);
    }
}
