package org.example.app.service;

import org.example.app.account.exception.AccountNotFoundException;
import org.example.app.account.model.Account;
import org.example.app.account.persistence.AccountRepository;
import org.example.app.account.service.AccountsService;
import org.example.app.account.service.KafkaMessagingServiceStub;
import org.example.app.account.service.MessagingServiceInterface;
import org.example.app.service.fixture.AccountRepositoryTest;
import org.example.app.service.fixture.AccountTestHelper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {



    @Test
    void whenValidCustomerIdIsPassed_thenReturnAccountList() {

        AccountRepository accountRepoMock = Mockito.mock(AccountRepository.class);
        MessagingServiceInterface messagingService = Mockito.mock(MessagingServiceInterface.class);
        Mockito.when(accountRepoMock.findByCustomerId("12345")).thenAnswer(invocationOnMock ->
                singletonList(AccountTestHelper.createTestAccount()));

        AccountsService service = new AccountsService(accountRepoMock, messagingService);
        List<Account> accountList = service.getAccounts("12345");

        assertEquals(123L, accountList.get(0).getNumber());

    }

    @Test
    void whenAccountDetailsArePassed_thenReturnAccountWithNumber() {
        Account account = AccountTestHelper.createTestAccount();
        AccountRepository accountRepoTestDouble = new AccountRepositoryTest();
        MessagingServiceInterface messagingService = new KafkaMessagingServiceStub();

        AccountsService service = new AccountsService(accountRepoTestDouble, messagingService);
        Account created_account = service.createAccount(account);

        assertNotNull(created_account.getNumber());
        assertEquals(101L, created_account.getNumber());


    }

    @Test
    void whenAccountDetailsArePassedUsingMockRepo_thenReturnAccountWithNumber() {
        Account account = AccountTestHelper.createTestAccount();

        AccountRepository accountRepoMock = Mockito.mock(AccountRepository.class);
        MessagingServiceInterface messagingService = Mockito.mock(MessagingServiceInterface.class);
        Mockito.when(accountRepoMock.save(account)).thenAnswer(invocationOnMock ->
        {
            account.setNumber(102L);
            return account;
        });


        AccountsService service = new AccountsService(accountRepoMock, messagingService);
        Account created_account = service.createAccount(account);

        assertNotNull(created_account.getNumber());
        assertEquals(102L, created_account.getNumber());


    }

    @Test
    void whenValidAccountNumberIsPassed_thenReturnAccountBalance() {
        Account account = AccountTestHelper.createTestAccount();

        AccountRepository accountRepoMock = Mockito.mock(AccountRepository.class);
        MessagingServiceInterface messagingService = Mockito.mock(MessagingServiceInterface.class);
        Mockito.when(accountRepoMock.findById(account.getNumber())).thenAnswer(invocationOnMock ->
                Optional.of(account));


        AccountsService service = new AccountsService(accountRepoMock, messagingService);
        BigDecimal accountBalance = service.getAccountBalance(account.getNumber());

        assertEquals(account.getBalance(), accountBalance);

    }

    @Test
    void whenInvalidAccountNumberIsPassed_thenThrowAccountNotFoundException() {
        Account account = AccountTestHelper.createTestAccount();
        AccountRepository accountRepoMock = Mockito.mock(AccountRepository.class);
        MessagingServiceInterface messagingService = Mockito.mock(MessagingServiceInterface.class);

        AccountsService service = new AccountsService(accountRepoMock, messagingService);

        Exception exception = assertThrows(AccountNotFoundException.class, () -> service.getAccountBalance(account.getNumber()));

        String expectedMessage = "Unable to find account with number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.startsWith(expectedMessage));

    }


}