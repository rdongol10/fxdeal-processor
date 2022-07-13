package com.rdongol.fxprocessor.service;

import com.rdongol.fxprocessor.exceptionHandler.AppException;
import com.rdongol.fxprocessor.model.FxDeal;
import com.rdongol.fxprocessor.utils.ErrorMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        AppException exception =
                assertThrows(AppException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), ErrorMessages.DEAL_ID_MISSING.getErrorMessage());
    }

    @Test
    void orderCurrencyCodeMissingTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        AppException exception =
                assertThrows(AppException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), ErrorMessages.ORDER_CURRENCY_MISSING.getErrorMessage());
    }

    @Test
    void toCurrencyCodeMissingTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        AppException exception =
                assertThrows(AppException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), ErrorMessages.TO_CURRENCY_MISSING.getErrorMessage());
    }

    @Test
    void dateTimeMissingTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        fxDeal.setToCurrencyCode("NPR");
        AppException exception =
                assertThrows(AppException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), ErrorMessages.ORDER_DATE_MISSING.getErrorMessage());

    }

    @Test
    void dealAmountIsZeroTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(0);
        AppException exception =
                assertThrows(AppException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), ErrorMessages.DEAL_AMOUNT_ZERO.getErrorMessage());
    }

    @Test
    void dealAmountLessThanZeroTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(-100);
        AppException exception =
                assertThrows(AppException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), ErrorMessages.DEAL_AMOUNT_LESS_THAN_ZERO.getErrorMessage());
    }

    @Test
    void invalidOrderCurrencyTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("RAM");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(100);
        AppException exception =
                assertThrows(AppException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), ErrorMessages.INVALID_ORDER_CURRENCY_CODE.getErrorMessage());
    }

    @Test
    void invalidToCurrencyCodeTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("NPR");
        fxDeal.setToCurrencyCode("RAM");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(100);
        AppException exception =
                assertThrows(AppException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), ErrorMessages.INVALID_TO_CURRENCY_CODE.getErrorMessage());
    }

    @Test
    void sameToAndOrderCurrencyCodeTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("NPR");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03T10:15:30");
        fxDeal.setDealAmount(100);
        AppException exception =
                assertThrows(AppException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), ErrorMessages.TO_AND_ORDER_CURRENCY_SAME.getErrorMessage());
    }

    @Test
    void invalidDateTimeTest() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealId("Test");
        fxDeal.setOrderCurrencyCode("USD");
        fxDeal.setToCurrencyCode("NPR");
        fxDeal.setDateTime("2011-12-03");
        fxDeal.setDealAmount(100);
        AppException exception =
                assertThrows(AppException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), ErrorMessages.INVALID_DATE.getErrorMessage());
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
        AppException exception =
                assertThrows(AppException.class, () -> fxDealProcessor.processFxDeal(fxDeal));
        assertEquals(exception.getMessage(), ErrorMessages.FX_DEAL_EXISTS.getErrorMessage() + " " + fxDeal.getDealId());
    }

    @Test
    void completeTest() {
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
