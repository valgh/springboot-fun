package com.example.microservices.limitsservices.controllers;

import com.example.microservices.limitsservices.Configuration;
import com.example.microservices.limitsservices.beans.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration config;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfiguration() {
        return new LimitConfiguration(config.getMax(), config.getMin());
    }
}
