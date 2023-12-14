package com.spring.sample.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PurchaseOrderRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(PurchaseOrderRepositoryTest.class);

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Test
    // @Sql("/data.sql")
    public void sampleTest(){

        logger.info(purchaseOrderRepository.findById(1L).toString());

        assert(true);
    }

}
