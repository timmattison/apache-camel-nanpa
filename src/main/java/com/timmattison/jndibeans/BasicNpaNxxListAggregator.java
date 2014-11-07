package com.timmattison.jndibeans;

import com.timmattison.jndibeans.interfaces.NpaNxxListAggregator;
import com.timmattison.nonjndibeans.interfaces.NpaNxx;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AbstractListAggregationStrategy;

/**
 * Created by timmattison on 11/4/14.
 */
public class BasicNpaNxxListAggregator extends AbstractListAggregationStrategy<NpaNxx> implements NpaNxxListAggregator {
    @Override
    public NpaNxx getValue(Exchange exchange) {
        // The value is the in body is cast to an NpaNxx object
        return exchange.getIn().getBody(NpaNxx.class);
    }
}
