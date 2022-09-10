package org.example.app.account.service;

import org.example.app.account.model.Account;

public interface MessagingServiceInterface {

    void publishAccountDetails(Account accountToBePublished);

    void publishAccountDetailsDummy(Account accountToBePublished);
}
