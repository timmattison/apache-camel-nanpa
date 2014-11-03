package com.timmattison.nonjndibeans.interfaces;

import com.timmattison.nonjndibeans.enums.InitialGrowth;
import com.timmattison.nonjndibeans.enums.UseCode;

import java.util.Date;

/**
 * Created by timmattison on 11/4/14.
 */
public interface NpaNxx {
    public static final String STATE = "state";
    public static final String NPA = "npa";
    public static final String NXX = "nxx";
    public static final String OCN = "ocn";
    public static final String COMPANY = "company";
    public static final String RATE_CENTER = "rateCenter";
    public static final String EFFECTIVE_DATE = "effectiveDate";
    public static final String USE_CODE = "useCode";
    public static final String ASSIGNED_DATE = "assignedDate";
    public static final String INITIAL_GROWTH = "initialGrowth";

    public String getState();

    public int getNpa();

    public int getNxx();

    public String getOcn();

    public String getCompany();

    public String getRateCenter();

    public Date getEffectiveDate();

    public UseCode getUseCode();

    public Date getAssignedDate();

    public InitialGrowth getInitialGrowth();

}
