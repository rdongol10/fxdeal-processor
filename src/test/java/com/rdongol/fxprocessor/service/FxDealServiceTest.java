package com.rdongol.fxprocessor.service;

import com.rdongol.fxprocessor.model.FxDeal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FxDealServiceTest {

    @Autowired
    FxDealService fxDealService;

    private FxDeal fxDeal;

    private void initializeFxDeal() {
        fxDeal = new FxDeal();
        fxDeal.setDealId("test");
        fxDeal.setOrderCurrencyCode("USD");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(100);
    }

    @Test
    void testAddFxDeal() {
        initializeFxDeal();
        Assertions.assertNotNull(fxDealService.addFxDeal(fxDeal));
    }

    @Test
    void tesForFxDealDoesExists() {
        initializeFxDeal();
        fxDealService.addFxDeal(fxDeal);
        Assertions.assertTrue(fxDealService.existsByDealId("test"));
    }

    @Test
    void tesForFxDealDoesNotExists() {
        Assertions.assertFalse(fxDealService.existsByDealId("test"));
    }
}
