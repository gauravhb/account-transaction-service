package org.example.app.account.dto;

import java.math.BigDecimal;

public class ExchangeConversionResponse {

    BigDecimal convertedAmount;
    String balanceCurrency;

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public String getBalanceCurrency() {
        return balanceCurrency;
    }

    public void setBalanceCurrency(String balanceCurrency) {
        this.balanceCurrency = balanceCurrency;
    }
}
