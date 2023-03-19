package com.example.microservices.limitsservices.beans;

public class LimitConfiguration {

    private Integer max;
    private Integer min;

    protected LimitConfiguration() {}

    public LimitConfiguration(Integer max, Integer min) {
        super();
        this.max = max;
        this.min = min;
    }

    public Integer getMax() {
        return this.max;
    }

    public Integer getMin() {
        return this.min;
    }

}
