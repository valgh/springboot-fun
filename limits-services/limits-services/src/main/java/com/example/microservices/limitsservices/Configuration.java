package com.example.microservices.limitsservices;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
public class Configuration {

    private Integer max;
    private Integer min;

    public void setMax(Integer max) {
        this.max=max;
    }

    public void setMin(Integer min) {
        this.min=min;
    }

    public Integer getMax() {
        return this.max;
    }

    public Integer getMin() {
        return this.min;
    }
}
