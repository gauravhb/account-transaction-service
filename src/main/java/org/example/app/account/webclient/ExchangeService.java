package org.example.app.account.webclient;

import java.math.BigDecimal;

public interface ExchangeService {

    BigDecimal convertBalance(BigDecimal inputBalance, String inputCurrency, String targetCurrency);

}
