package com.simple.account.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.simple.account.dao.Account;

@Service
public class AccountServices {
	private final Map<Long, Account> accounts = new HashMap<>();

	   public AccountServices() {
	       
	        accounts.put(1L, new Account(1L, "Aditi", "ACC111","USD", BigDecimal.valueOf(5000.00)));
	        accounts.put(2L, new Account(2L, "Amey", "ACC222", "USD", BigDecimal.valueOf(7500.50)));
	        accounts.put(3L, new Account(3L, "Kavya", "ACC333","USD", BigDecimal.valueOf(8000.00)));
	        accounts.put(4L, new Account(4L, "Ram", "ACC444", "USD", BigDecimal.valueOf(9500.50)));
	    }
    public Account getAccountById(Long id) {
        return accounts.get(id);
    }

    public Account deposit(Long id, BigDecimal amount) {
        Account account = accounts.get(id);
        if (account != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            account.setBalance(account.getBalance().add(amount));
        }
        return account;
    }

    public Account withdraw(Long id, BigDecimal amount) {
        Account account = accounts.get(id);
        if (account != null && amount.compareTo(BigDecimal.ZERO) > 0 
            && account.getBalance().compareTo(amount) >= 0) {
            account.setBalance(account.getBalance().subtract(amount));
        }
        return account;
    }
}

