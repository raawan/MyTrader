package com.acme.mytrader.price;

import com.acme.mytrader.strategy.TradingStrategy;

public interface PriceSource {
    void addPriceListener(PriceListener listener);

    void removePriceListener(PriceListener listener);

    void addTradingStrategy(TradingStrategy tradingStrategy);

    void notifyTradingStrategy(PriceListener listener);
}
