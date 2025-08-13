package org.example.app.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.example.app.account.webclient.ExchangeServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WireMockTest(httpPort = 8082)
public class ExchangeServiceIntegrationTest {



    @Test
    void whenAmountIsPassedInUSD_covertBalanceToEuro(){

        BigDecimal amount = new BigDecimal(100);
        String fromCurrency = "USD";
        String toCurrency =  "EUR";

        stubFor(get(urlPathEqualTo("/forex/from/" + fromCurrency + "/to/" + toCurrency))
                .willReturn(okJson("{\n" +
                        "  \"exchangeRate\" : \"1.10\",\n" +
                        "  \"fromCurrency\" : \"USD\",\n" +
                        "  \"toCurrency\" : \"EURO\"\n" +
                        "}")));

        ExchangeServiceImpl exchangeService = new ExchangeServiceImpl();

        BigDecimal balanceResponse = exchangeService
                .convertBalance(amount, fromCurrency, toCurrency);

        assertEquals(0, new BigDecimal("110").compareTo(balanceResponse));

    }
}
