package com.timmattison;

import javax.naming.InitialContext;

/**
 * Created by timmattison on 10/27/14.
 */
public class CamelApplication {
    public static void main(String[] args) throws Exception {
        // Create the Camel context with Guice
        InitialContext context = new InitialContext();

        // Loop forever
        while (true) {
            // Sleep so we don't kill the CPU
            Thread.sleep(10000);
        }
    }
}
