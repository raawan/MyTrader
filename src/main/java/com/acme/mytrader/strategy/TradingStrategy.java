package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

    private final ExecutionService executionService;
    private final PriceSource priceSource;

    public TradingStrategy(final ExecutionService executionService, final PriceSource priceSource) {
        this.executionService = executionService;
        this.priceSource = priceSource;
    }

    public void executeStrategy(final PriceListener listener) {

        if(listener.getSecurity().equalsIgnoreCase("IBM") && listener.getPrice()<50) {
            executionService.buy(listener.getSecurity(),listener.getPrice(),100);
        }

    }
}
