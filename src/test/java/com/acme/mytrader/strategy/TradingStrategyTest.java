package com.acme.mytrader.strategy;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @Test
    public void testNothing() {
        //Given
        priceSource = new PriceSourceImpl();
        new TradingStrategy(executionService, priceSource);
        doNothing().when(executionService).buy(anyString(), anyDouble(), anyInt());

        PriceListener priceListener = new PriceListenerImpl();
        priceListener.priceUpdate("IBM", 45.0);

        //When
        priceSource.addPriceListener(priceListener);

        //Then
        verify(executionService, times(1)).buy(anyString(), anyDouble(), anyInt());

    }
}
