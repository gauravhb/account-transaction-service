package org.example.app.service;

import org.example.app.account.exception.AccountNotFoundException;
import org.example.app.account.model.Account;
import org.example.app.account.persistence.AccountRepository;
import org.example.app.account.service.AccountsService;
import org.example.app.account.service.KafkaMessagingServiceStub;
import org.example.app.account.service.MessagingServiceInterface;
import org.example.app.service.fixture.AccountRepositoryTest;
import org.example.app.service.fixture.AccountTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;


public class AccountServiceImprovedTest {


    @Mock private AccountRepository accountRepository;
    @Mock private MessagingServiceInterface messagingService;
    @InjectMocks private AccountsService accountService;

    @BeforeEach
    void init()
    {
        MockitoAnnotations.openMocks( this );
    }
    @Test
    void whenValidCustomerIdIsPassed_thenReturnAccountList() {


        Mockito.when(accountRepository.findByCustomerId("12345")).thenAnswer(invocationOnMock ->
                singletonList(AccountTestHelper.createTestAccount()));
        List<Account> accountList = accountService.getAccounts("12345");

        assertEquals(123L, accountList.get(0).getNumber());

    }

    @Test
    void whenAccountDetailsArePassed_thenReturnAccountWithNumberStub() {
        Account account = AccountTestHelper.createTestAccount();
        AccountRepository accountRepoTestStub = new AccountRepositoryTest();

        AccountsService service = new AccountsService(accountRepoTestStub, messagingService);
        Account created_account = service.createAccount(account);

        assertNotNull(created_account.getNumber());
        assertEquals(101L, created_account.getNumber());


    }

    @Test
    void whenAccountDetailsArePassedUsingMockRepo_thenReturnAccountWithNumber() {
        Account account = AccountTestHelper.createTestAccount();

        Mockito.when(accountRepository.save(account)).thenAnswer(invocationOnMock ->
        {
            account.setNumber(102L);
            return account;
        });

        Account created_account = accountService.createAccount(account);

        assertNotNull(created_account.getNumber());
        assertEquals(102L, created_account.getNumber());


    }

    @Test
    void whenValidAccountNumberIsPassed_thenReturnAccountBalance() {
        Account account = AccountTestHelper.createTestAccount();

        Mockito.when(accountRepository.findById(account.getNumber())).thenAnswer(invocationOnMock ->
                Optional.of(account));

        BigDecimal accountBalance = accountService.getAccountBalance(account.getNumber());

        assertEquals(account.getBalance(), accountBalance);

    }

    @Test
    void whenInValidAccountNumberIsPassed_thenThrowAccountNotFoundException() {
        Account account = AccountTestHelper.createTestAccount();

        Exception exception = assertThrows(AccountNotFoundException.class, () -> accountService.getAccountBalance(account.getNumber()));

        String expectedMessage = "Unable to find account with number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.startsWith(expectedMessage));

    }

    @Test
    void whenValidAccountNumberIsPassed_thenVerifyMessageIsPublishedStub(){

        AccountRepository accountRepoTestStub = new AccountRepositoryTest();
        KafkaMessagingServiceStub kafkaMessagingServiceStub = new KafkaMessagingServiceStub();

        AccountsService service = new AccountsService(accountRepoTestStub, kafkaMessagingServiceStub);
        service.publishAccountDetails(101L);

        String stubMessage = kafkaMessagingServiceStub.getSentMessages().get(101L);

        assertTrue(stubMessage.contains("Message sent successfully"));
    }

    @Test
    void whenValidAccountNumberIsPassed_thenVerifyMessageIsPublishedMock(){
        Account account = AccountTestHelper.createTestAccount();
        Mockito.when(accountRepository.findById(account.getNumber())).thenAnswer(invocationOnMock ->
                Optional.of(account));
        accountService.publishAccountDetails(account.getNumber());

        Mockito.verify(messagingService, times(1)).publishAccountDetails(account);
    }
}