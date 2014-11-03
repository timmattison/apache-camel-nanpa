package com.timmattison.nonjndibeans;

import com.google.inject.assistedinject.Assisted;
import com.timmattison.nonjndibeans.enums.InitialGrowth;
import com.timmattison.nonjndibeans.enums.UseCode;
import com.timmattison.nonjndibeans.interfaces.NpaNxx;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Date;

/**
 * Created by timmattison on 11/4/14.
 */
public class BasicNpaNxx implements NpaNxx {
    private final String state;

    private final int npa;

    private final int nxx;

    private final String ocn;

    private final String company;

    private final String rateCenter;

    private final Date effectiveDate;

    private final UseCode useCode;

    private final Date assignedDate;

    private final InitialGrowth initialGrowth;

    @Inject
    public BasicNpaNxx(@Assisted(STATE) String state,
                       @Assisted(NPA) int npa,
                       @Assisted(NXX) int nxx,
                       @Assisted(OCN) String ocn,
                       @Assisted(COMPANY) String company,
                       @Assisted(RATE_CENTER) String rateCenter,
                       @Nullable @Assisted(EFFECTIVE_DATE) Date effectiveDate,
                       @Assisted(USE_CODE) UseCode useCode,
                       @Nullable @Assisted(ASSIGNED_DATE) Date assignedDate,
                       @Nullable @Assisted(INITIAL_GROWTH) InitialGrowth initialGrowth) {
        this.state = state;
        this.npa = npa;
        this.nxx = nxx;
        this.ocn = ocn;
        this.company = company;
        this.rateCenter = rateCenter;
        this.effectiveDate = effectiveDate;
        this.useCode = useCode;
        this.assignedDate = assignedDate;
        this.initialGrowth = initialGrowth;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public int getNpa() {
        return npa;
    }

    @Override
    public int getNxx() {
        return nxx;
    }

    @Override
    public String getOcn() {
        return ocn;
    }

    @Override
    public String getCompany() {
        return company;
    }

    @Override
    public String getRateCenter() {
        return rateCenter;
    }

    @Override
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    @Override
    public UseCode getUseCode() {
        return useCode;
    }

    @Override
    public Date getAssignedDate() {
        return assignedDate;
    }

    @Override
    public InitialGrowth getInitialGrowth() {
        return initialGrowth;
    }
}
