package com.simple.account.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.account.dao.Account;
import com.simple.account.services.AccountServices;
@RestController
@RequestMapping("/accounts")
public class AccountController {
	private final AccountServices accountService;

    public AccountController(AccountServices accountService) {
        this.accountService = accountService;
    }

   
//      Get account 
     
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    
//      Deposit 
     
    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return accountService.deposit(id, amount);
    }

    
//      Withdraw
    
    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return accountService.withdraw(id, amount);
    }
}
