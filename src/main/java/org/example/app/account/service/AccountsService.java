package org.example.app.account.service;

import org.example.app.account.exception.AccountNotFoundException;
import org.example.app.account.model.Account;
import org.example.app.account.persistence.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AccountsService implements AccountsServiceInterface{


    private AccountRepository accountRepository;
    private MessagingServiceInterface messagingService;

    public AccountsService(@Autowired AccountRepository accountRepository,
                           @Autowired MessagingServiceInterface messagingService) {
        this.accountRepository = accountRepository;
        this.messagingService = messagingService;
    }


    public List<Account> getAccounts(String customerId) {
        Objects.requireNonNull(customerId, "CustomerId should not be null");
        return accountRepository.findByCustomerId(customerId);


    }

    public Account createAccount(Account account){
        return accountRepository.save(account);

    }

    public BigDecimal getAccountBalance(Long accountNumber){
        Optional<Account> accountOpt = accountRepository.findById(accountNumber);
        return accountOpt.map(Account::getBalance)
                .orElseThrow(() -> new AccountNotFoundException("Unable to find account with number : " + accountNumber));

    }

    public void publishAccountDetails(Long accountNumber){

        Optional<Account> accountOpt = accountRepository.findById(accountNumber);
        Account accountDetails = accountOpt.
                orElseThrow(() -> new AccountNotFoundException("Unable to find account with number : " + accountNumber));

        messagingService.publishAccountDetails(accountDetails);
    }

    public BigDecimal getAccountBalanceInCurrency(String accountNumber, String currencyType){

        //Validate CurrencyType with one given in account entity
        //Get Exchange Rate from Remote Webservice
        //Apply conversion and return

        return BigDecimal.ZERO;
    }




}
