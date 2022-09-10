package org.example.app.account.service;

import org.example.app.account.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountsServiceInterface {

    List<Account> getAccounts(String customerId);

    void publishUpdatedBalance(Long accountNumber, BigDecimal amountTobeUpdated);

}
