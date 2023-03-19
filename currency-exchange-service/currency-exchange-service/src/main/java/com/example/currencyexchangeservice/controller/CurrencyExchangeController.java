package com.example.currencyexchangeservice.controller;

import com.example.currencyexchangeservice.model.ExchangeValue;
import com.example.currencyexchangeservice.repository.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@SpringBootApplication
@RestController
public class CurrencyExchangeController {
    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));
        return exchangeValue;
    }

    @PostMapping("/exchange/save")
    public ResponseEntity<HttpStatus> saveExchangeValue(@RequestBody ExchangeValue exchangeValue) {
        ExchangeValue savedValue = exchangeValueRepository.save(exchangeValue);
        if (savedValue != null)
            return ResponseEntity.ok(HttpStatus.OK);
        else
            return ResponseEntity.noContent().build();
    }
}
