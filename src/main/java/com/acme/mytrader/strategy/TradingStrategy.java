package com.acme.mytrader.strategy;

import java.util.List;

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
    private final List<SecurityTriggerLevel> securityTriggerLevels;

    public TradingStrategy(final ExecutionService executionService,
                           final PriceSource priceSource,
                           final List<SecurityTriggerLevel> securityTriggerLevels) {
        this.executionService = executionService;
        this.priceSource = priceSource;
        this.securityTriggerLevels = securityTriggerLevels;
        priceSource.addTradingStrategy(this);
    }

    public void executeStrategy(final PriceListener listener) {

        securityTriggerLevels.stream()
                .filter(securityTriggerLevel -> triggerFilter(securityTriggerLevel, listener))
                .forEach(securityTriggerLevel -> buySecurity(securityTriggerLevel, listener));
    }

    private void buySecurity(final SecurityTriggerLevel securityTriggerLevel, final PriceListener listener) {
        executionService.buy(listener.getSecurity(), listener.getPrice(), securityTriggerLevel.getVolume());
    }

    private boolean triggerFilter(final SecurityTriggerLevel securityTriggerLevel, final PriceListener listener) {
        if (securityTriggerLevel.getSecurity().equalsIgnoreCase(listener.getSecurity()) &&
                securityTriggerLevel.getPrice() >= listener.getPrice()) {
            return true;
        }
        return false;
    }

}
