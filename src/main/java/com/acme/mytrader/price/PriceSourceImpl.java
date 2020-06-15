package com.acme.mytrader.price;

import java.util.HashMap;
import java.util.Map;

public class PriceSourceImpl implements PriceSource {

    private Map<String, Double> priceListeners = new HashMap<>();

    @Override  
    public void addPriceListener(final PriceListener listener) {
        priceListeners.put(listener.getSecurity(), listener.getPrice());
    }

    @Override
    public void removePriceListener(final PriceListener listener) {
        priceListeners.remove(listener);
    }
}
