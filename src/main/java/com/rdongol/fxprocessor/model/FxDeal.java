package com.rdongol.fxprocessor.model;

import com.rdongol.fxprocessor.exceptionHandler.exception.AppException;
import com.rdongol.fxprocessor.utils.ErrorMessages;
import com.rdongol.fxprocessor.utils.StringUtils;
import com.rdongol.fxprocessor.utils.ValidationUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FxDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dealId;
    private String orderCurrencyCode;
    private String toCurrencyCode;
    private String dateTime;
    private double dealAmount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getOrderCurrencyCode() {
        return orderCurrencyCode;
    }

    public void setOrderCurrencyCode(String orderCurrencyCode) {
        this.orderCurrencyCode = orderCurrencyCode;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(double orderingCurrency) {
        this.dealAmount = orderingCurrency;
    }

    public void validate() throws AppException {
        if (StringUtils.isEmpty(this.dealId)) {
            throw new AppException(ErrorMessages.DEAL_ID_MISSING.getErrorMessage());
        }

        if (StringUtils.isEmpty(this.orderCurrencyCode)) {
            throw new AppException(ErrorMessages.ORDER_CURRENCY_MISSING.getErrorMessage());
        }

        if (StringUtils.isEmpty(this.toCurrencyCode)) {
            throw new AppException(ErrorMessages.TO_CURRENCY_MISSING.getErrorMessage());
        }

        if (StringUtils.isEmpty(this.dateTime)) {
            throw new AppException(ErrorMessages.ORDER_DATE_MISSING.getErrorMessage());
        }

        if (dealAmount == 0.0) {
            throw new AppException(ErrorMessages.DEAL_AMOUNT_ZERO.getErrorMessage());
        }

        if (dealAmount < 0) {
            throw new AppException(ErrorMessages.DEAL_AMOUNT_LESS_THAN_ZERO.getErrorMessage());
        }

        if (!ValidationUtils.isValidCurrencyCode(this.orderCurrencyCode)) {
            throw new AppException(ErrorMessages.INVALID_ORDER_CURRENCY_CODE.getErrorMessage());
        }

        if (!ValidationUtils.isValidCurrencyCode(this.toCurrencyCode)) {
            throw new AppException(ErrorMessages.INVALID_TO_CURRENCY_CODE.getErrorMessage());
        }

        if (this.orderCurrencyCode.equalsIgnoreCase(this.toCurrencyCode)) {
            throw new AppException(ErrorMessages.TO_AND_ORDER_CURRENCY_SAME.getErrorMessage());
        }

        if (!ValidationUtils.isValidISODateTime(this.dateTime)) {
            throw new AppException(ErrorMessages.INVALID_DATE.getErrorMessage());
        }

    }


}
