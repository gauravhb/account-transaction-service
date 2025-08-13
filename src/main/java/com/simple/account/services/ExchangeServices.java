package com.simple.account.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ExchangeServices {
 private static final Map<String,Double> exchangeRates=new HashMap<>();
 
 static 
 {
	 exchangeRates.put("USD", 1.0);
	 exchangeRates.put("INR", 83.0);
	 exchangeRates.put("EUR", 0.92);
	 exchangeRates.put("GBP", 0.78);
	 exchangeRates.put("JPY", 142.0);
	 exchangeRates.put("USD", 1.0);
	 exchangeRates.put("USD", 1.0);
	 
	 
 }
 public double getExchangeRate(String fromCurrency,String toCurrency) {
	 Double fromRate = exchangeRates.get(fromCurrency.toUpperCase());
	 Double toRate = exchangeRates.get(toCurrency.toUpperCase());
	 if(fromRate == null ||toRate==null) {
		throw new IllegalArgumentException("Invaid currency code");
	 }
	 //convert usd as base
	 return toRate / fromRate;
 }
 public double convertAmount(String fromCurrency,String toCurrency,double amount) {
	 double rate=getExchangeRate(fromCurrency, toCurrency);
	 return amount*rate;
 }
 
}