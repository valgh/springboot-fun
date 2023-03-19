package com.example.currencyexchangeservice.repository;

import com.example.currencyexchangeservice.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    ExchangeValue findByFromAndTo(String from, String to);

    ExchangeValue save(ExchangeValue exchangeValue);
}
