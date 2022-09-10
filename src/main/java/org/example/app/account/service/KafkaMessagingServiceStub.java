package org.example.app.account.service;

import org.example.app.account.model.Account;

import java.util.HashMap;
import java.util.Map;

public class KafkaMessagingServiceStub implements MessagingServiceInterface {

    private Map<Long, String> sentMessageMap = new HashMap<>();

    @Override
    public void publishAccountDetails(Account accountToBePublished) {
        String message = "Message sent successfully for account number : " + accountToBePublished.getNumber();
        // Faking message publishing to  Kafka topic
        sentMessageMap.put(accountToBePublished.getNumber(), message);
        System.out.println(message);
    }

    @Override
    public void publishAccountDetailsDummy(Account accountToBePublished) {

    }

    public Map<Long, String>  getSentMessages(){
        return sentMessageMap;
    }


}
