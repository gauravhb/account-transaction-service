package org.example.app.service;

import org.example.app.account.exception.AccountNotFoundException;
import org.example.app.account.model.Account;
import org.example.app.account.persistence.AccountRepository;
import org.example.app.account.service.AccountsService;
import org.example.app.account.service.KafkaMessagingServiceStub;
import org.example.app.account.service.MessagingService;
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


class AccountServiceImprovedTest {


    @Mock private AccountRepository accountRepository;
    @Mock private MessagingService messagingService;
    @InjectMocks private AccountsService accountService;

    private Account account ;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        account = AccountTestHelper.createTestAccount();
    }


    @Test
    void whenValidCustomerIdIsPassed_thenReturnAccountList() {


        Mockito.when(accountRepository.findByCustomerId("12345")).thenAnswer(invocationOnMock ->
                singletonList(AccountTestHelper.createTestAccount()));
        List<Account> accountList = accountService.getAccounts("12345");

        assertEquals(123L, accountList.get(0).getId());

    }

    @Test
    void whenAccountDetailsArePassed_thenReturnAccountWithNumberStub() {

        AccountRepository accountRepoTestStub = new AccountRepositoryTest();

        AccountsService service = new AccountsService(accountRepoTestStub, messagingService);
        Account created_account = service.createAccount(account);

        assertEquals(101L, created_account.getId());


    }

    @Test
    void whenAccountDetailsArePassedUsingMockRepo_thenReturnAccountWithNumber() {

        Mockito.when(accountRepository.save(account)).thenAnswer(invocationOnMock ->
        {
            account.setId(102L);
            return account;
        });

        Account created_account = accountService.createAccount(account);

        assertNotNull(created_account.getId());
        assertEquals(102L, created_account.getId());


    }

    @Test
    void whenValidAccountNumberIsPassed_thenReturnAccountBalance() {

        Mockito.when(accountRepository.findById(account.getId())).thenAnswer(invocationOnMock ->
                Optional.of(account));

        BigDecimal accountBalance = accountService.getAccountBalance(account.getId());

        assertEquals(account.getBalance(), accountBalance);

    }

    @Test
    void whenInValidAccountNumberIsPassed_thenThrowAccountNotFoundException() {

        Exception exception = assertThrows(AccountNotFoundException.class, () -> accountService.getAccountBalance(account.getId()));

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

        Mockito.when(accountRepository.findById(account.getId())).thenAnswer(invocationOnMock ->
                Optional.of(account));
        accountService.publishAccountDetails(account.getId());

        Mockito.verify(messagingService, times(1)).publishAccountDetails(account);
    }
}