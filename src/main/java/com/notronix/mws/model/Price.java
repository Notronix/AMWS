package com.notronix.mws.model;

import com.amazonservices.mws.products.model.MoneyType;

import java.math.BigDecimal;

public class Price
{
    private String currency;
    private double amount = 0.0;

    public Price(String currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public MoneyType toMoneyType() {
        return new MoneyType().withCurrencyCode(currency).withAmount(BigDecimal.valueOf(amount));
    }
}
