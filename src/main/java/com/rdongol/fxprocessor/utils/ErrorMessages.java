package com.rdongol.fxprocessor.utils;

public enum ErrorMessages {

    DEAL_ID_MISSING("Deal id is missing"),
    ORDER_CURRENCY_MISSING("Order currency code is missing"),
    TO_CURRENCY_MISSING("to currency code is missing"),
    ORDER_DATE_MISSING("order date is missing"),
    DEAL_AMOUNT_ZERO("Deal amount is cannot be 0"),
    DEAL_AMOUNT_LESS_THAN_ZERO("Deal amount is cannot less than 0"),
    INVALID_ORDER_CURRENCY_CODE("Invalid order currency code"),
    INVALID_TO_CURRENCY_CODE("Invalid to currency code"),
    TO_AND_ORDER_CURRENCY_SAME("Order currency code and to currency code can not be same"),
    INVALID_DATE("Invalid date"),
    FX_DEAL_EXISTS("Fx deal already exists :");
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
