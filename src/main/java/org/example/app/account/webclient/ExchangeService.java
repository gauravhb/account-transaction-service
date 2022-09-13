package org.example.app.account.webclient;

import org.example.app.account.dto.ExchangeConversionResponse;

import java.math.BigDecimal;

public interface ExchangeService {

    ExchangeConversionResponse convertBalance(BigDecimal inputBalance, String inputCurrency, String targetCurrency);

}
