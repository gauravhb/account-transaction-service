 package com.simple.account.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.simple.account.dao.Transancation;

@Service
public class MessagingServices {
	private final JavaMailSender mailSender;

    public MessagingServices (JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendTransactionMessage(Transancation transaction, String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Transaction Notification");
        message.setText("Dear Customer,\n\nYour transaction of " 
                        + transaction.getAmount() + " " + transaction.getCurrency() 
                        + " was successful.\nAccount: " + transaction.getAccountNumber() 
                        + "\nTime: " + transaction.getTimestamp());

        mailSender.send(message);
    }
}


