package com.timmattison.jndibeans.interfaces;

import com.timmattison.nonjndibeans.interfaces.NpaNxx;
import org.apache.camel.Processor;

import java.util.List;

/**
 * Caches and provides NPA-NXX data
 */
public interface NpaNxxDataProvider extends Processor {
    public NpaNxx getNpaNxx(int npa, int nxx);

    public void update(List<NpaNxx> npaNxxList);
}
