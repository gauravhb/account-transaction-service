package com.simple.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.account.dao.Transancation;
import com.simple.account.services.MessagingServices;

//import jakarta.transaction.Transaction;

@RestController
@RequestMapping("/messaging")
public class MessagingController {
	private final MessagingServices messagingService;

    public MessagingController(MessagingServices messagingService) {
        this.messagingService = messagingService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String email,
                                              @RequestBody Transancation transaction) {
        messagingService.sendTransactionMessage(transaction, email);
        return ResponseEntity.ok("Message sent successfully to " + email);
    }
}


