package org.example.app.service;

import org.example.app.account.exception.AccountNotFoundException;
import org.example.app.account.model.Account;
import org.example.app.account.persistence.AccountRepository;
import org.example.app.account.service.AccountsService;
import org.example.app.account.service.KafkaMessagingServiceStub;
import org.example.app.account.service.MessagingService;
import org.example.app.service.fixture.AccountRepositoryTest;
import org.example.app.service.fixture.AccountTestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {



    @Test
    void whenValidCustomerIdIsPassed_thenReturnAccountList() {

        AccountRepository accountRepoMock = Mockito.mock(AccountRepository.class);
        MessagingService messagingService = Mockito.mock(MessagingService.class);
        Mockito.when(accountRepoMock.findByCustomerId("12345")).thenAnswer(invocationOnMock ->
                singletonList(AccountTestHelper.createTestAccount()));

        AccountsService service = new AccountsService(accountRepoMock, messagingService);
        List<Account> accountList = service.getAccounts("12345");

        assertEquals(123L, accountList.get(0).getId());

    }

    @Test
    void whenAccountDetailsArePassed_thenReturnAccountWithNumber() {
        Account account = AccountTestHelper.createTestAccount();
        AccountRepository accountRepoTestDouble = new AccountRepositoryTest();
        MessagingService messagingService = new KafkaMessagingServiceStub();

        AccountsService service = new AccountsService(accountRepoTestDouble, messagingService);
        Account created_account = service.createAccount(account);

        assertNotNull(created_account.getId());
        assertEquals(101L, created_account.getId());


    }

    @Test
    void whenAccountDetailsArePassedUsingMockRepo_thenReturnAccountWithNumber() {
        Account account = AccountTestHelper.createTestAccount();

        AccountRepository accountRepoMock = Mockito.mock(AccountRepository.class);
        MessagingService messagingService = Mockito.mock(MessagingService.class);
        Mockito.when(accountRepoMock.save(account)).thenAnswer(invocationOnMock ->
        {
            account.setId(102L);
            return account;
        });


        AccountsService service = new AccountsService(accountRepoMock, messagingService);
        Account created_account = service.createAccount(account);

        assertNotNull(created_account.getId());
        assertEquals(102L, created_account.getId());


    }

    @Test
    void whenValidAccountNumberIsPassed_thenReturnAccountBalance() {
        Account account = AccountTestHelper.createTestAccount();

        AccountRepository accountRepoMock = Mockito.mock(AccountRepository.class);
        MessagingService messagingService = Mockito.mock(MessagingService.class);
        Mockito.when(accountRepoMock.findById(account.getId())).thenAnswer(invocationOnMock ->
                Optional.of(account));


        AccountsService service = new AccountsService(accountRepoMock, messagingService);
        BigDecimal accountBalance = service.getAccountBalance(account.getId());

        assertEquals(account.getBalance(), accountBalance);

    }

    @Test
    void whenInvalidAccountNumberIsPassed_thenThrowAccountNotFoundException() {
        Account account = AccountTestHelper.createTestAccount();
        AccountRepository accountRepoMock = Mockito.mock(AccountRepository.class);
        MessagingService messagingService = Mockito.mock(MessagingService.class);

        AccountsService service = new AccountsService(accountRepoMock, messagingService);

        Exception exception = assertThrows(AccountNotFoundException.class, () -> service.getAccountBalance(account.getId()));

        String expectedMessage = "Unable to find account with number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.startsWith(expectedMessage));

    }

    @ParameterizedTest
    @CsvSource(value = {"test:TEST", "tEst:TEST", "Java:JAVA"}, delimiter = ':')
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValueCSVSource(
            String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }


}