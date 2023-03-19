package com.example.currencyexchangeservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
@Entity
@Table(name="Exchange_Value")
public class ExchangeValue {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="currency_from")
    private String from;
    @Column(name="currency_to")
    private String to;
    @Column(name="conversion_multiple")
    private BigDecimal conversionMultiple;
    @Column(name="port")
    private int port;

    public ExchangeValue(){}

    public ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id=id;
        this.from=from;
        this.to=to;
        this.conversionMultiple=conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

}
