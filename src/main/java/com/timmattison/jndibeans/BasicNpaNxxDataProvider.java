package com.timmattison.jndibeans;

import com.timmattison.jndibeans.interfaces.NpaNxxDataProvider;
import com.timmattison.nonjndibeans.interfaces.NpaNxx;
import org.apache.camel.Exchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by timmattison on 11/4/14.
 */
public class BasicNpaNxxDataProvider implements NpaNxxDataProvider {
    public static final String NPA = "npa";
    public static final String NXX = "nxx";
    private Map<String, NpaNxx> cache = new HashMap<String, NpaNxx>();

    public NpaNxx getNpaNxx(int npa, int nxx) {
        // Return the cached data for the value requested
        return cache.get(convertToString(npa, nxx));
    }

    public void update(List<NpaNxx> npaNxxList) {
        // Create a new cache
        Map<String, NpaNxx> newCache = new HashMap<String, NpaNxx>();

        // Is there any data?
        if (npaNxxList == null) {
            // No, replace the old cache with the new (empty) cache and return immediately
            cache = newCache;
            return;
        }

        // Loop through all of the NpaNxx data
        for (NpaNxx npaNxx : npaNxxList) {
            // Add each entry to the new cache
            newCache.put(convertToString(npaNxx.getNpa(), npaNxx.getNxx()), npaNxx);
        }

        // Replace the old cache with the new cache
        cache = newCache;
    }

    private String convertToString(int npa, int nxx) {
        // Convert the two numbers to strings and concatenate them
        return String.valueOf(npa) + String.valueOf(nxx);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        // Convert the NPA and NXX header values to integers so we validate that the input is sane
        int npa = Integer.parseInt((String) exchange.getIn().getHeader(NPA));
        int nxx = Integer.parseInt((String) exchange.getIn().getHeader(NXX));

        // Get the NpaNxx data from the cache and set it as the out body
        exchange.getOut().setBody(getNpaNxx(npa, nxx));
    }
}
