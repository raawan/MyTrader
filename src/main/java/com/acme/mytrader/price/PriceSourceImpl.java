package com.acme.mytrader.price;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acme.mytrader.strategy.TradingStrategy;

public class PriceSourceImpl implements PriceSourceSubject {

    private Map<String, Double> priceListeners = new HashMap<>();
    private List<TradingStrategy> tradingStrategies = new ArrayList<>();

    @Override
    public void addPriceListener(final PriceListener listener) {
        priceListeners.put(listener.getSecurity(), listener.getPrice());
        notifyTradingStrategy(listener);
    }

    @Override
    public void removePriceListener(final PriceListener listener) {
        priceListeners.remove(listener);
    }

    @Override
    public void addTradingStrategy(TradingStrategy tradingStrategy) {
        tradingStrategies.add(tradingStrategy);
    }

    @Override
    public void notifyTradingStrategy(final PriceListener listener) {
        tradingStrategies.forEach(tradingStrategy -> tradingStrategy.executeStrategy(listener));
    }

}
