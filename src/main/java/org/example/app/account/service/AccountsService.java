package org.example.app.account.service;

import org.example.app.account.dto.ExchangeConversionResponse;
import org.example.app.account.exception.AccountNotFoundException;
import org.example.app.account.model.Account;
import org.example.app.account.persistence.AccountRepository;
import org.example.app.account.webclient.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountsService implements AccountsServiceInterface{


    private AccountRepository accountRepository;
    private MessagingService messagingService;
    private ExchangeService exchangeService;

    public AccountsService(@Autowired AccountRepository accountRepository,
                           @Autowired MessagingService messagingService) {
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

    public BigDecimal getAccountBalance(Long id){

        Optional<Account> accountOpt = accountRepository.findById(id);
        return accountOpt.map(Account::getBalance)
                .orElseThrow(() -> new AccountNotFoundException("Unable to find account with number : " + id));

    }

    public void publishAccountDetails(Long accountNumber){

        Optional<Account> accountOpt = accountRepository.findById(accountNumber);
        Account accountDetails = accountOpt.
                orElseThrow(() -> new AccountNotFoundException("Unable to find account with number : " + accountNumber));

        messagingService.publishAccountDetails(accountDetails);
    }

    public BigDecimal getAccountBalanceInCurrency(Long accountNumber){

        //Validate CurrencyType with one given in account entity
        //Get Exchange Rate from Remote Webservice
        //Apply conversion and return

        BigDecimal convertedAmount = accountRepository.findById(accountNumber)
                .map(account -> {
                    ExchangeConversionResponse response = exchangeService.convertBalance(account.getBalance(), "USD", "EUR");
                    return response.getConvertedAmount();
                }).orElseThrow(() -> new RuntimeException("Unable to get converted balance for accountNumber : " + accountNumber));

        return convertedAmount;
    }




}
