package com.timmattison.nonjndibeans.factories;

import com.google.inject.assistedinject.Assisted;
import com.timmattison.nonjndibeans.enums.InitialGrowth;
import com.timmattison.nonjndibeans.enums.UseCode;
import com.timmattison.nonjndibeans.BasicNpaNxx;
import com.timmattison.nonjndibeans.interfaces.NpaNxx;

import java.util.Date;

/**
 * Created by timmattison on 11/4/14.
 */
public interface NpaNxxFactory {
    public NpaNxx create(@Assisted(NpaNxx.STATE) String state,
                         @Assisted(NpaNxx.NPA) int npa,
                         @Assisted(NpaNxx.NXX) int nxx,
                         @Assisted(NpaNxx.OCN) String ocn,
                         @Assisted(NpaNxx.COMPANY) String company,
                         @Assisted(NpaNxx.RATE_CENTER) String rateCenter,
                         @Assisted(NpaNxx.EFFECTIVE_DATE) Date effectiveDate,
                         @Assisted(NpaNxx.USE_CODE) UseCode useCode,
                         @Assisted(NpaNxx.ASSIGNED_DATE) Date assignedDate,
                         @Assisted(NpaNxx.INITIAL_GROWTH) InitialGrowth initialGrowth);
}
