package org.example.app.account.dto;

import java.math.BigDecimal;

public class AccountBalanceResponse {
    private Long accountNumber;
    private BigDecimal accountBalance;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}
