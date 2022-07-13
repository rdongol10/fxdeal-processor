package com.rdongol.fxprocessor.service;

import com.rdongol.fxprocessor.model.FxDeal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import javax.xml.bind.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class FxDealProcessorTest {

    @InjectMocks
    FxDealProcessor fxDealProcessor;

    @Mock
    FxDealService fxDealService;

    @Test
    void dealIdMissingTest() {
        FxDeal fxDeal = new FxDeal();
        ValidationException exception =
                assertThrows(ValidationException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), "Deal id is missing");
    }

    @Test
    void orderCurrencyCodeMissingTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        ValidationException exception =
                assertThrows(ValidationException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), "Order currency code is missing");
    }

    @Test
    void toCurrencyCodeMissingTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        ValidationException exception =
                assertThrows(ValidationException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), "to currency code is missing");
    }

    @Test
    void dateTimeMissingTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        fxDeal.setToCurrencyCode("NPR");
        ValidationException exception =
                assertThrows(ValidationException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), "order date is missing");

    }

    @Test
    void dealAmountIsZeroTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(0);
        ValidationException exception =
                assertThrows(ValidationException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), "Deal amount is cannot be 0");
    }

    @Test
    void dealAmountLessThanZeroTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(-100);
        ValidationException exception =
                assertThrows(ValidationException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), "Deal amount is cannot less than 0");
    }

    @Test
    void invalidOrderCurrencyTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("RAM");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(100);
        ValidationException exception =
                assertThrows(ValidationException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), "Invalid order currency code RAM");
    }

    @Test
    void invalidToCurrencyCodeTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("NPR");
        fxDeal.setToCurrencyCode("RAM");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(100);
        ValidationException exception =
                assertThrows(ValidationException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), "Invalid to currency code RAM");
    }

    @Test
    void sameToAndOrderCurrencyCodeTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("NPR");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(100);
        ValidationException exception =
                assertThrows(ValidationException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), "Order currency code and to currency code can not be same");
    }

    @Test
    void invalidDateTimeTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03");
        fxDeal.setDealAmount(100);
        ValidationException exception =
                assertThrows(ValidationException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), "Invalid date 2011-12-03");
    }

    @Test
    void entityAlreadyExistsTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(100);
        Mockito.when(fxDealService.existsByDealId(Mockito.any())).thenReturn(true);
        EntityExistsException exception =
                assertThrows(EntityExistsException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), "Fx deal Test already exists");
    }

    @Test
    void completeTest() throws Exception {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setId(1L);
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(100);
        Mockito.when(fxDealService.existsByDealId(Mockito.any())).thenReturn(false);
        Mockito.when(fxDealService.addFxDeal(Mockito.any())).thenReturn(fxDeal);
        assertEquals(fxDealProcessor.processFxDeal(fxDeal), 1);
    }

}
