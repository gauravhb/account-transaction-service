package com.simple.account.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transaction;
@Repository
public interface TransanctionRepositories extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);


}
