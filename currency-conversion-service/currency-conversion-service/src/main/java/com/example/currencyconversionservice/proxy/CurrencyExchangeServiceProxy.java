package com.example.currencyconversionservice.proxy;

import com.example.currencyconversionservice.beans.CurrencyConversionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="api-gateway", url="http://localhost:8080/")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to);
}
