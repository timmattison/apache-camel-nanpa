package com.timmattison;

import com.timmattison.jndibeans.interfaces.NpaNxxDataProvider;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;

/**
 * Created by timmattison on 10/27/14.
 */
public class RestRoutes extends RouteBuilder {
    private static final String RESTLET = "restlet";
    private static final int PORT = 8000;
    private static final String GET_NPA_NXX_ROUTE = "direct:get_npa_nxx";
    private static final String NPA_NXX_URL = "/npaNxx/{npa}/{nxx}";

    @Override
    public void configure() throws Exception {
        restConfiguration().bindingMode(RestBindingMode.auto).component(RESTLET).port(PORT);

        // Map GET requests to NPA_NXX_URL to the direct GET_NPA_NXX_ROUTE
        rest(NPA_NXX_URL)
                .get().to(GET_NPA_NXX_ROUTE);

        // Process all requests from the GET_NPA_NXX_ROUTE through the NpaNxxDataProvider and convert the result to JSON using Gson
        from(GET_NPA_NXX_ROUTE)
                .beanRef(NpaNxxDataProvider.class.getName())
                .marshal().json(JsonLibrary.Gson);
    }
}
