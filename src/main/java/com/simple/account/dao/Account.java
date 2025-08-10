package com.simple.account.dao;

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
	private long cid;
	private String cname;
	private String accountnumber;
	private String currency;
	private Double balance;
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
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
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [cid=" + cid + ", cname=" + cname + ", accountnumber=" + accountnumber + ", currency="
				+ currency + ", balance=" + balance + "]";
	}
	
	

}
