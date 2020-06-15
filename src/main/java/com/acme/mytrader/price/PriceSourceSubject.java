package com.acme.mytrader.price;

import com.acme.mytrader.strategy.TradingStrategy;

public interface PriceSourceSubject extends PriceSource {

    void addTradingStrategy(TradingStrategy tradingStrategy);

    void notifyTradingStrategy(PriceListener listener);
}
