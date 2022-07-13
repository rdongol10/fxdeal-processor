package com.rdongol.fxprocessor.model;

import com.rdongol.fxprocessor.utils.StringUtils;
import com.rdongol.fxprocessor.utils.ValidationUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.ValidationException;

@Entity
public class FxDeal {

    @Id
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

    public void validate() throws Exception {
        if (StringUtils.isEmpty(this.dealId)) {
            throw new ValidationException("Deal id is missing");
        }

        if (StringUtils.isEmpty(this.orderCurrencyCode)) {
            throw new ValidationException("Order currency code is missing");
        }

        if (StringUtils.isEmpty(this.toCurrencyCode)) {
            throw new ValidationException("to currency code is missing");
        }

        if (StringUtils.isEmpty(this.dateTime)) {
            throw new ValidationException("order date is missing");
        }

        if (dealAmount == 0.0) {
            throw new ValidationException("Deal amount is cannot be 0");
        }

        if (dealAmount < 0) {
            throw new ValidationException("Deal amount is cannot less than 0");
        }

        if (!ValidationUtils.isValidCurrencyCode(this.orderCurrencyCode)) {
            throw new ValidationException("Invalid order currency code " + this.orderCurrencyCode);
        }

        if (!ValidationUtils.isValidCurrencyCode(this.toCurrencyCode)) {
            throw new ValidationException("Invalid to currency code " + this.toCurrencyCode);
        }

        if (this.orderCurrencyCode.equalsIgnoreCase(this.toCurrencyCode)) {
            throw new ValidationException("Order currency code and to currency code can not be same");
        }

        if (!ValidationUtils.isValidISODateTime(this.dateTime)) {
            throw new ValidationException("Invalid date " + this.dateTime);
        }

    }


}
