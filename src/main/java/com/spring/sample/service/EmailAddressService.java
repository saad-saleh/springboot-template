package com.spring.sample.service;

import com.spring.sample.exception.CustomException;
import com.spring.sample.exception.ErrorCode;
import com.spring.sample.model.EmailAddress;
import com.spring.sample.repository.EmailAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EmailAddressService {

    private static final Logger logger = LoggerFactory.getLogger(EmailAddressService.class);

    @Autowired
    private EmailAddressRepository emailAddressRepository;

    public Page<EmailAddress> getEmailAddresses(Specification<EmailAddress> specification, Pageable pageable) {
        return emailAddressRepository.findAll(specification, pageable);
    }

    public EmailAddress getEmailAddress(int emailAddressId) {
        EmailAddress emailAddress = emailAddressRepository.findById(emailAddressId);
        if (emailAddress == null) {
            throw new CustomException("EmailAddress not found", ErrorCode.NOT_FOUND);
        }
        return emailAddress;
    }

    public EmailAddress createEmailAddress(EmailAddress emailAddress) {
        EmailAddress createdEmailAddress = emailAddressRepository.save(emailAddress);
        return createdEmailAddress;
    }

    public EmailAddress editEmailAddress(int emailAddressId, EmailAddress emailAddress) {
        logger.info("editEmailAddress>>emailAddressId:{}>>requestEmailAddress:{}", emailAddressId, emailAddress);

        EmailAddress retrievedEmailAddress = getEmailAddress(emailAddressId);
        retrievedEmailAddress.setCustomer(emailAddress.getCustomer());
        // Set other properties as needed
        emailAddressRepository.save(retrievedEmailAddress);
        return retrievedEmailAddress;
    }

    public void deleteEmailAddress(int emailAddressId) {
        EmailAddress emailAddress = getEmailAddress(emailAddressId);
        emailAddressRepository.delete(emailAddress);
    }
}
