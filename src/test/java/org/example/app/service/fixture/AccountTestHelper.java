package org.example.app.service.fixture;

import org.example.app.account.model.Account;

import java.math.BigDecimal;

public class AccountTestHelper {

    public static Account createTestAccount(){
        Account account = new Account();
        account.setCustomerId("12345");
        account.setName("EURO Cash Account");
        account.setType("Cash_Account");
        account.setBalance(new BigDecimal("150000"));
        account.setId(123L);
        return account;
    }
}
