package com.rdongol.fxprocessor.utils;

import java.util.Currency;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtils {
    public static boolean isValidCurrencyCode(String currencyCode) {
        Set<Currency> currencies = Currency.getAvailableCurrencies();
        return currencies.stream().
                map(currency -> currency.getCurrencyCode().toUpperCase()).collect(Collectors.toList()).
                contains(currencyCode.toUpperCase());
    }

    public static boolean isValidISODateTime(String date) {
        try {
            java.time.format.DateTimeFormatter.ISO_DATE_TIME.parse(date);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }
}
