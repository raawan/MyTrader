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
import com.acme.mytrader.price.PriceSourceImpl;
import com.acme.mytrader.price.PriceSourceSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {

    @Mock
    ExecutionService executionService;
    PriceSourceSubject priceSource;
    List<SecurityTriggerLevel> securityTriggerLevels;

    @Before
    public void setUp() {
        doNothing().when(executionService).buy(anyString(), anyDouble(), anyInt());

        securityTriggerLevels = new ArrayList<>();
        priceSource = new PriceSourceImpl();
        new TradingStrategy(executionService, priceSource, securityTriggerLevels);
    }

    @Test
    public void shouldCorrectTradingStrategyGetsExecutedOnReceivingSecurityPriceUpdateThatMeetsTheTriggerLevel() {
        //Given
        SecurityTriggerLevel securityTriggerLevel = new SecurityTriggerLevel("IBM", 50.0, 100);
        securityTriggerLevels.add(securityTriggerLevel);
        PriceListener priceListener = new PriceListenerImpl();
        priceListener.priceUpdate("IBM", 45.0);

        //When
        priceSource.addPriceListener(priceListener);

        //Then
        verify(executionService, times(1)).buy(anyString(), anyDouble(), anyInt());

    }

    @Test
    public void shouldNoTradingStrategyGetsExecutedOnReceivingSecurityPriceUpdateThatDoNotMeetsTheTriggerLevel() {

        //Given
        SecurityTriggerLevel securityTriggerLevel = new SecurityTriggerLevel("CISCO", 70.0, 100);
        securityTriggerLevels.add(securityTriggerLevel);
        PriceListener priceListener = new PriceListenerImpl();
        priceListener.priceUpdate("CISCO", 75.0);

        //When
        priceSource.addPriceListener(priceListener);

        //Then
        verify(executionService, times(0)).buy(anyString(), anyDouble(), anyInt());
    }
}
