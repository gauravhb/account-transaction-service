package org.example.app.account.persistence;

import org.example.app.account.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByCustomerId(String customerId);

    /*Optional<Account> findByNumber(Long accountNumber);*/

}
