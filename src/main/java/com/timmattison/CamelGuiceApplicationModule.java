package com.timmattison;

import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.timmattison.jndibeans.*;
import com.timmattison.jndibeans.interfaces.*;
import com.timmattison.nonjndibeans.BasicNpaNxx;
import com.timmattison.nonjndibeans.factories.NpaNxxFactory;
import com.timmattison.nonjndibeans.interfaces.NpaNxx;
import org.apache.camel.guice.CamelModuleWithMatchingRoutes;

/**
 * Created by timmattison on 10/27/14.
 */
public class CamelGuiceApplicationModule extends CamelModuleWithMatchingRoutes {
    @Override
    protected void configure() {
        // Call the configure method of the super class or your Camel routes will not start!
        super.configure();

        // Use the basic file loading processor to get data from the local disk or via HTTP
        bind(FileLoadingProcessor.class).to(BasicFileLoadingProcessor.class);

        // Use the basic record processor to handle the individual records
        bind(NpaNxxRecordProcessor.class).to(BasicNpaNxxRecordProcessor.class);

        // Use the basic list aggregator to aggregate the records into a list
        bind(NpaNxxListAggregator.class).to(BasicNpaNxxListAggregator.class);

        // Use the basic data provider, as a singleton, as our cache
        bind(NpaNxxDataProvider.class).to(BasicNpaNxxDataProvider.class).in(Singleton.class);

        // Use the basic provider updater to update the cache
        bind(NpaNxxProviderUpdater.class).to(BasicNpaNxxProviderUpdater.class);

        // Automatically build a factory for NpaNxx objects using the BasicNpaNxx implementation
        install(new FactoryModuleBuilder()
                .implement(NpaNxx.class, BasicNpaNxx.class)
                .build(NpaNxxFactory.class));

        // Wire up the REST routes
        bind(RestRoutes.class);

        // Wire up the scheduled (Quartz2) routes
        bind(ScheduledRoutes.class);
    }
}
