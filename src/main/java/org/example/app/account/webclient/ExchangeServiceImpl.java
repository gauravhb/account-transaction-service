package org.example.app.account.webclient;

import org.example.app.account.dto.ForexServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ExchangeServiceImpl implements ExchangeService {

    private static final String FOREX_SERVICE_BASE_URL = "http://localhost:8082/forex/";

    private static final String FOREX_ENDPOINT = "from/{from}/to/{to}";


    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public BigDecimal convertBalance(BigDecimal amount, String inputCurrency, String targetCurrency) {

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("from", inputCurrency);
        pathParams.put("to", targetCurrency);
        pathParams.put("amount", amount.toPlainString());


        ResponseEntity<ForexServiceResponse> response = restTemplate
                .getForEntity(FOREX_SERVICE_BASE_URL + FOREX_ENDPOINT, ForexServiceResponse.class, pathParams);

        if (response.getStatusCode().is2xxSuccessful()) {
            ForexServiceResponse forexServiceResponse = response.getBody();
            if (forexServiceResponse != null && forexServiceResponse.getExchangeRate() != null) {
                return amount.multiply(forexServiceResponse.getExchangeRate());
            } else {
                throw new RuntimeException("Unable to get exchange rate from remote service");
            }
        } else {
            throw new RuntimeException("Error invoking Forex Service : " + response.getStatusCodeValue());
        }


    }
}
