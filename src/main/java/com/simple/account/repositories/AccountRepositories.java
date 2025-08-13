package com.simple.account.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simple.account.dao.Account;
@Repository
public interface AccountRepositories extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}
