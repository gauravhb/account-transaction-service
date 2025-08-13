package org.example.app.account;

import org.example.app.account.dto.AccountBalanceResponse;
import org.example.app.account.model.Account;
import org.example.app.account.service.AccountsService;
import org.example.app.account.service.AccountsServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountsController {

    AccountsServiceInterface accountsService;

    public AccountsController(AccountsServiceInterface accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping(value = "/balance/{accountNumber}")
    public AccountBalanceResponse getBalance(@PathVariable("accountNumber") Long accountNumber){

        BigDecimal balance = accountsService.getAccountBalance(accountNumber);

        AccountBalanceResponse response = new AccountBalanceResponse();
        response.setAccountNumber(accountNumber);
        response.setAccountBalance(balance);

        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody Account account){

        Account created_account = accountsService.createAccount(account);

        return created_account;
    }


}
