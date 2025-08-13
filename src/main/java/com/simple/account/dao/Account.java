package com.simple.account.dao;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Account {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountId;
	private String customerName;
	private String accountnumber;
	private String currency;
	private BigDecimal  balance;
	
	public Account(long accountId, String customerName, String accountnumber, String currency, BigDecimal balance) {
		
		this.accountId = accountId;
		this.customerName = customerName;
		this.accountnumber = accountnumber;
		this.currency = currency;
		this.balance = balance;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	   
	   


}
