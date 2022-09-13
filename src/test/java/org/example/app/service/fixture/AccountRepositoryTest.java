package org.example.app.service.fixture;

import org.example.app.account.model.Account;
import org.example.app.account.persistence.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountRepositoryTest implements AccountRepository {
    @Override
    public Account save(Account entity) {
        entity.setId(101L);
        return entity;
    }



    @Override
    public <S extends Account> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Account> findById(Long accountNumber) {
        if(accountNumber == 101L){
            Account account = AccountTestHelper.createTestAccount();
            account.setId(101L);
            return Optional.of(account);
        }
        return Optional.empty();

    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Account> findAll() {
        return null;
    }

    @Override
    public Iterable<Account> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Account entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Account> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Account> findByCustomerId(String customerId) {
        return null;
    }

    /*@Override
    public Optional<Account> findByNumber(Long accountNumber) {
        return Optional.empty();
    }*/
}
