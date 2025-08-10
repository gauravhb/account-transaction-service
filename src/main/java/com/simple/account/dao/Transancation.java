package com.simple.account.dao;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class Transancation {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long accountId;
	    private String type; // DEPOSIT, WITHDRAWAL, TRANSFER
	    private Double amount;
	    private String accountNumber;
	    private String currency;
	    private String timestamp;
		public Long getAccountId() {
			return accountId;
		}
		public void setAccountId(Long accountId) {
			this.accountId = accountId;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public String getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		@Override
		public String toString() {
			return "Transancation [accountId=" + accountId + ", type=" + type + ", amount=" + amount
					+ ", accountNumber=" + accountNumber + ", currency=" + currency + ", timestamp=" + timestamp + "]";
		}
	    
}