package org.example.app.account.webclient;

import org.example.app.account.dto.ExchangeConversionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ExchangeServiceImpl implements ExchangeService {

    private static final String FOREX_SERVICE_BASE_URL = "http://localhost:8082/forex/";

    private static final String FOREX_ENDPOINT = "from/{from}/to/{to}/amount/{amount}";

    @Autowired
    RestTemplate restTemplate;



    @Override
    public ExchangeConversionResponse convertBalance(BigDecimal amount, String inputCurrency, String targetCurrency) {

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("from", inputCurrency);
        pathParams.put("to", targetCurrency);
        pathParams.put("amount", amount.toPlainString());


        ResponseEntity<ExchangeConversionResponse> response = restTemplate
                .getForEntity(FOREX_SERVICE_BASE_URL + FOREX_ENDPOINT, ExchangeConversionResponse.class, pathParams);

        return response.getBody();

    }
}
