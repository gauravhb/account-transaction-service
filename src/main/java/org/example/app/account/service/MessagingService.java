package org.example.app.account.service;

import org.example.app.account.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface MessagingService {

    void publishAccountDetails(Account accountToBePublished);

    void publishAccountDetailsDummy(Account accountToBePublished);
}
