package com.acme.mytrader.price;

public class PriceListenerImpl implements PriceListener {

    private String security;
    private double price;

    @Override
    public void priceUpdate(final String security, final double price) {
        this.security = security;
        this.price = price;
    }

    @Override
    public String getSecurity() {
        return security;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
