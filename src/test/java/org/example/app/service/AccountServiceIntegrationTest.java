package org.example.app.service;

import org.example.app.account.model.Account;
import org.example.app.account.persistence.AccountRepository;
import org.example.app.account.service.AccountsService;
import org.example.app.account.service.MessagingService;
import org.example.app.service.fixture.AccountTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class AccountServiceIntegrationTest {

    @Autowired
    private AccountRepository accountRepo;


    private MessagingService messagingService;


    @Test
    void whenAccountDetailsArePassed_thenCreateAccountWithNumber(){
        Account account = AccountTestHelper.createTestAccount();

        AccountsService service = new AccountsService(accountRepo, messagingService);
        Account created_account = service.createAccount(account);

        assertEquals(1L, created_account.getId());
    }

    @Test
    void whenCustomerIdIsPassed_thenReturnListOfAccount(){
        Account account = AccountTestHelper.createTestAccount();

        AccountsService service = new AccountsService(accountRepo, messagingService);
        service.createAccount(account);

        List<Account> accounts = service.getAccounts("12345");
        assertEquals(1, accounts.size());
        assertEquals("EURO Cash Account", accounts.get(0).getName());

    }

    @Test
    void whenCustomerIdIsPassed_thenReturnAccountBalance(){
        Account account = AccountTestHelper.createTestAccount();

        AccountsService service = new AccountsService(accountRepo, messagingService);
        account = service.createAccount(account);

        BigDecimal balance = service.getAccountBalance(account.getId());
        assertEquals(new BigDecimal("150000"), balance);


    }

    private Account createSampleAccount() {
        Account account = new Account();
        account.setCustomerId("12345");
        account.setName("EURO Cash Account");
        account.setType("Cash_Account");
        account.setBalance(new BigDecimal("150000"));
        return account;
    }
}
