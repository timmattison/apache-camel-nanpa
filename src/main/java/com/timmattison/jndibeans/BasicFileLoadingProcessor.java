package com.timmattison.jndibeans;

import com.timmattison.jndibeans.interfaces.FileLoadingProcessor;
import org.apache.camel.Exchange;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class BasicFileLoadingProcessor implements FileLoadingProcessor {
    public static final String FILE = "file://";
    public static final String HTTP = "http://";

    @Override
    public void process(Exchange exchange) throws Exception {
        // Get the URI from the body
        String uriString = (String) exchange.getIn().getBody();

        // What does the URI string start with?
        if (uriString.startsWith(HTTP)) {
            // It starts with "http://", fetch the data via HTTP
            processHttpUrl(exchange, uriString);
        } else if (uriString.startsWith(FILE)) {
            // It starts with "file://", fetch the data from disk
            processFile(exchange, uriString);
        } else {
            // It starts with something unexpected, throw an exception
            throw new UnsupportedOperationException("Unsupported URI scheme [" + uriString + "]");
        }
    }

    private void processHttpUrl(Exchange exchange, String urlString) throws IOException {
        // Grab all of the data into a byte array
        URL url = new URL(urlString);
        byte[] data = IOUtils.toByteArray(url.openStream());

        // Set the byte array as the out body
        exchange.getOut().setBody(data);
    }

    private void processFile(Exchange exchange, String uriString) throws URISyntaxException, FileNotFoundException {
        // Create a file object from the URI
        URI uri = new URI(uriString);
        File file = new File(uri);

        // Does the file exist?
        if (!file.exists()) {
            // No, throw an exception
            throw new FileNotFoundException(String.format("File %s not found", uriString));
        }

        // Set the file as the out body
        exchange.getIn().setBody(file);
    }
}
