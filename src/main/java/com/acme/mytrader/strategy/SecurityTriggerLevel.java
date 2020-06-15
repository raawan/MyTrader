package com.acme.mytrader.strategy;

public class SecurityTriggerLevel {

    private String security;
    private double price;
    private int volume;

    public SecurityTriggerLevel(final String security, final double price, final int volume) {
        this.security = security;
        this.price = price;
        this.volume = volume;
    }

    public String getSecurity() {
        return security;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }
}
