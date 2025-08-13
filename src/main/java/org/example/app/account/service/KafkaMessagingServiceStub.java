package org.example.app.account.service;

import org.example.app.account.model.Account;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaMessagingServiceStub implements MessagingService {

    private Map<Long, String> sentMessageMap = new HashMap<>();

    @Override
    public void publishAccountDetails(Account accountToBePublished) {
        String message = "Message sent successfully for account number : " + accountToBePublished.getId();
        // Faking message publishing to  Kafka topic
        sentMessageMap.put(accountToBePublished.getId(), message);
        System.out.println(message);
    }

    @Override
    public void publishAccountDetailsDummy(Account accountToBePublished) {
        System.out.println(accountToBePublished);
    }

    public Map<Long, String>  getSentMessages(){
        return sentMessageMap;
    }


}
