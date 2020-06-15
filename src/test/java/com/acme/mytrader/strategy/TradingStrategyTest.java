package com.acme.mytrader.strategy;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceListenerImpl;
import com.acme.mytrader.price.PriceSource;
import com.acme.mytrader.price.PriceSourceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {

    @Mock
    ExecutionService executionService;
    PriceSource priceSource;
    List<SecurityTriggerLevel> securityTriggerLevels;

    @Test
    public void shouldCorrectTradingStrategyGetsExecutedOnReceivingSecurityPriceUpdateThatMeetsTheTriggerLevel() {
        //Given
        securityTriggerLevels = new ArrayList<>();
        SecurityTriggerLevel securityTriggerLevel = new SecurityTriggerLevel("IBM", 50.0, 100);
        securityTriggerLevels.add(securityTriggerLevel);

        priceSource = new PriceSourceImpl();
        final TradingStrategy tradingStrategy = new TradingStrategy(executionService, priceSource, securityTriggerLevels);
        doNothing().when(executionService).buy(anyString(), anyDouble(), anyInt());

        priceSource.addTradingStrategy(tradingStrategy);

        PriceListener priceListener = new PriceListenerImpl();
        priceListener.priceUpdate("IBM", 45.0);

        //When
        priceSource.addPriceListener(priceListener);

        //Then
        verify(executionService, times(1)).buy(anyString(), anyDouble(), anyInt());

    }
}
