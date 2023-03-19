package com.example.currencyconversionservice.controller;

import com.example.currencyconversionservice.beans.CurrencyConversionBean;
import com.example.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @GetMapping("/converter/from/{from}/to/{to}/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity("http://localhost:8080/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class,
                uriVariables);
        CurrencyConversionBean response = responseEntity.getBody();
        return computeConversion(response, quantity);
    }

    @GetMapping("/converter-feign/from/{from}/to/{to}/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        CurrencyConversionBean currencyConversionBean = proxy.convertCurrencyFeign(from, to);
        return computeConversion(currencyConversionBean, quantity);
    }

    private static CurrencyConversionBean computeConversion(CurrencyConversionBean response, BigDecimal quantity) {
        if (response != null && quantity != null) {
            return new CurrencyConversionBean(
                    response.getId(),
                    response.getFrom(),
                    response.getTo(),
                    response.getConversionMultiple(),
                    quantity,
                    quantity.multiply(response.getConversionMultiple()),
                    response.getPort()
            );
        } else {
            return null; // not a brilliant exception handling but this will do for an example
        }
    }

}
