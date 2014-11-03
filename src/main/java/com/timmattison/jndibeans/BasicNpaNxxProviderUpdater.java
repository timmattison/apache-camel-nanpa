package com.timmattison.jndibeans;

import com.google.inject.Inject;
import com.timmattison.jndibeans.interfaces.NpaNxxProviderUpdater;
import com.timmattison.nonjndibeans.interfaces.NpaNxx;
import com.timmattison.jndibeans.interfaces.NpaNxxDataProvider;
import org.apache.camel.Exchange;

import java.util.List;

/**
 * Created by timmattison on 11/4/14.
 */
public class BasicNpaNxxProviderUpdater implements NpaNxxProviderUpdater {
    private final NpaNxxDataProvider npaNxxDataProvider;

    @Inject
    public BasicNpaNxxProviderUpdater(NpaNxxDataProvider npaNxxDataProvider) {
        this.npaNxxDataProvider = npaNxxDataProvider;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        // Get the new NpaNxx list
        List<NpaNxx> npaNxxList = (List<NpaNxx>) exchange.getIn().getBody();

        // Update the data provider with it
        npaNxxDataProvider.update(npaNxxList);
    }
}
