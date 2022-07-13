package com.rdongol.fxprocessor.model;

import com.rdongol.fxprocessor.utils.ErrorMessages;
import com.rdongol.fxprocessor.utils.StringUtils;
import com.rdongol.fxprocessor.utils.ValidationUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.ValidationException;
import java.util.logging.Level;
import java.util.logging.Logger;

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


    protected static final Logger LOGGER = Logger.getLogger(FxDeal.class.getName());

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

    public void validate() throws Exception {
        if (StringUtils.isEmpty(this.dealId)) {
            LOGGER.log(Level.SEVERE, ErrorMessages.DEAL_ID_MISSING.getErrorMessage());
            throw new ValidationException(ErrorMessages.DEAL_ID_MISSING.getErrorMessage());
        }

        if (StringUtils.isEmpty(this.orderCurrencyCode)) {
            LOGGER.log(Level.SEVERE, ErrorMessages.ORDER_CURRENCY_MISSING.getErrorMessage());
            throw new ValidationException(ErrorMessages.ORDER_CURRENCY_MISSING.getErrorMessage());
        }

        if (StringUtils.isEmpty(this.toCurrencyCode)) {
            LOGGER.log(Level.SEVERE, ErrorMessages.TO_CURRENCY_MISSING.getErrorMessage());
            throw new ValidationException(ErrorMessages.TO_CURRENCY_MISSING.getErrorMessage());
        }

        if (StringUtils.isEmpty(this.dateTime)) {
            LOGGER.log(Level.SEVERE, ErrorMessages.ORDER_DATE_MISSING.getErrorMessage());
            throw new ValidationException(ErrorMessages.ORDER_DATE_MISSING.getErrorMessage());
        }

        if (dealAmount == 0.0) {
            LOGGER.log(Level.SEVERE, ErrorMessages.DEAL_AMOUNT_ZERO.getErrorMessage());
            throw new ValidationException(ErrorMessages.DEAL_AMOUNT_ZERO.getErrorMessage());
        }

        if (dealAmount < 0) {
            LOGGER.log(Level.SEVERE, ErrorMessages.DEAL_AMOUNT_LESS_THAN_ZERO.getErrorMessage());
            throw new ValidationException(ErrorMessages.DEAL_AMOUNT_LESS_THAN_ZERO.getErrorMessage());
        }

        if (!ValidationUtils.isValidCurrencyCode(this.orderCurrencyCode)) {
            LOGGER.log(Level.SEVERE, ErrorMessages.INVALID_ORDER_CURRENCY_CODE.getErrorMessage());
            throw new ValidationException(ErrorMessages.INVALID_ORDER_CURRENCY_CODE.getErrorMessage());
        }

        if (!ValidationUtils.isValidCurrencyCode(this.toCurrencyCode)) {
            LOGGER.log(Level.SEVERE, ErrorMessages.INVALID_TO_CURRENCY_CODE.getErrorMessage());
            throw new ValidationException(ErrorMessages.INVALID_TO_CURRENCY_CODE.getErrorMessage());
        }

        if (this.orderCurrencyCode.equalsIgnoreCase(this.toCurrencyCode)) {
            LOGGER.log(Level.SEVERE, ErrorMessages.TO_AND_ORDER_CURRENCY_SAME.getErrorMessage());
            throw new ValidationException(ErrorMessages.TO_AND_ORDER_CURRENCY_SAME.getErrorMessage());
        }

        if (!ValidationUtils.isValidISODateTime(this.dateTime)) {
            LOGGER.log(Level.SEVERE, ErrorMessages.INVALID_DATE.getErrorMessage());
            throw new ValidationException(ErrorMessages.INVALID_DATE.getErrorMessage());
        }

    }


}
