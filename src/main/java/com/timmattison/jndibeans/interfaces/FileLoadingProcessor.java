package com.timmattison.jndibeans.interfaces;

import org.apache.camel.Processor;

/**
 * Loads a "file://" or "http://" URI and set the exchange's out body to the data it loads
 */
public interface FileLoadingProcessor extends Processor {
}
