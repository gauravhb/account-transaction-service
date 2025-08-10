package com.simple.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.account.services.ExchangeServices;

@RestController
@RequestMapping("/exchange")
public class CurrencyExchangeController {

	 private final  ExchangeServices currencyService;

	    public CurrencyExchangeController(ExchangeServices currencyService) {
	        this.currencyService = currencyService;
	    }

	    // Exchange Rate
	    @GetMapping("/rate")
	    public double getRate(@RequestParam String from, @RequestParam String to) {
	        return currencyService.getExchangeRate(from, to);
	    }

	    //  Converted Amount
	    @GetMapping("/convert")
	    public double convert(
	            @RequestParam String from,
	            @RequestParam String to,
	            @RequestParam double amount) {
	        return currencyService.convertAmount(from, to, amount);
	    }
	}


