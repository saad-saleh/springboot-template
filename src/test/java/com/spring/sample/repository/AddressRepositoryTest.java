package com.spring.sample.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AddressRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(AddressRepositoryTest.class);

    @Autowired
    AddressRepository addressRepository;

    @Test
    // @Sql("/data.sql")
    public void sampleTest(){

        assert(true);
    }

}
