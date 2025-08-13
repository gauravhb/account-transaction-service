package org.example.app.account.service;

import org.example.app.account.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountsServiceInterface {

    List<Account> getAccounts(String customerId);

    void publishAccountDetails(Long accountNumber);

    public Account createAccount(Account account);

    BigDecimal getAccountBalance(Long accountNumber);

    BigDecimal getAccountBalanceInCurrency(Long accountNumber);
}
