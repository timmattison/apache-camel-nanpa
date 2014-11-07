package com.timmattison;

import com.timmattison.jndibeans.interfaces.FileLoadingProcessor;
import com.timmattison.jndibeans.interfaces.NpaNxxListAggregator;
import com.timmattison.jndibeans.interfaces.NpaNxxProviderUpdater;
import com.timmattison.jndibeans.interfaces.NpaNxxRecordProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.flatpack.FlatpackDataFormat;

/**
 * Created by timmattison on 10/27/14.
 */
public class ScheduledRoutes extends RouteBuilder {
    public static final String MOCK_ERROR = "mock:error";
    public static final String scheduledRefreshDataRoute = "quartz2://refreshGroup/scheduledNpaNxxRefresh?cron=0 0 * * * ?";
    public static final String immediateRefreshDataRoute = "timer://runOnce?repeatCount=1&delay=5000";
    public static final String fileUri = "file://allutlzd.zip";
    public static final String httpUri = "http://www.nanpa.com/nanp1/allutlzd.zip";
    public static final char HORIZONTAL_TAB = (char) 0x09;

    @Override
    public void configure() throws Exception {
        // Send all errors to the mock error channel.  Don't leave this in a production environment because bad messages will pile up here indefinitely!
        errorHandler(deadLetterChannel(MOCK_ERROR));

        // Use Flatpack to process the raw data.  Split fields on horizontal tabs and ignore the first record.
        FlatpackDataFormat flatpackDataFormat = new FlatpackDataFormat();
        flatpackDataFormat.setDelimiter(HORIZONTAL_TAB);
        flatpackDataFormat.setIgnoreFirstRecord(true);

        // Using the specified format build two scheduled routes.  The first runs once daily at midnight, the second runs once at startup after a 5 second delay.
        buildRefreshRoute(flatpackDataFormat, scheduledRefreshDataRoute);
        buildRefreshRoute(flatpackDataFormat, immediateRefreshDataRoute);
    }

    private void buildRefreshRoute(FlatpackDataFormat flatpackDataFormat, String route) {
        from(route)
                .log(LoggingLevel.INFO, "Update starting")
                        // Fetch the data from nanpa.com
                .setBody(constant(httpUri))
                .log(LoggingLevel.INFO, "Reading file")
                .beanRef(FileLoadingProcessor.class.getName())
                .log(LoggingLevel.INFO, "Unzipping file")
                        // Unzip it
                .unmarshal().zipFile()
                .log(LoggingLevel.INFO, "Processing with Flatpack")
                        // Convert each line to a Map with Flatpack
                .unmarshal(flatpackDataFormat)
                .log(LoggingLevel.INFO, "Splitting into lines and processing")
                        // Process each line and create a list of NpaNxx objects
                .split(body()).aggregationStrategyRef(NpaNxxListAggregator.class.getName()).beanRef(NpaNxxRecordProcessor.class.getName()).end()
                .log(LoggingLevel.INFO, "Updating cache")
                        // Update the cache
                .beanRef(NpaNxxProviderUpdater.class.getName())
                .log(LoggingLevel.INFO, "Updating complete");
    }
}
