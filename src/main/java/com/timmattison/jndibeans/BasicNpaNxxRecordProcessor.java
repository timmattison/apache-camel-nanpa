package com.timmattison.jndibeans;

import com.google.inject.Inject;
import com.timmattison.jndibeans.interfaces.NpaNxxRecordProcessor;
import com.timmattison.nonjndibeans.enums.InitialGrowth;
import com.timmattison.nonjndibeans.enums.UseCode;
import com.timmattison.nonjndibeans.factories.NpaNxxFactory;
import com.timmattison.nonjndibeans.interfaces.NpaNxx;
import org.apache.camel.Exchange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by timmattison on 10/27/14.
 */
public class BasicNpaNxxRecordProcessor implements NpaNxxRecordProcessor {
    public static final String STATE = "State";
    public static final String NPA_NXX = "NPA-NXX";
    public static final String OCN = "OCN";
    public static final String COMPANY = "Company";
    public static final String RATE_CENTER = "RateCenter";
    public static final String EFFECTIVE_DATE = "EffectiveDate";
    public static final String USE = "Use";
    public static final String ASSIGN_DATE = "AssignDate";
    public static final String INITIAL_GROWTH = "Initial/Growth";
    private final NpaNxxFactory npaNxxFactory;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

    @Inject
    public BasicNpaNxxRecordProcessor(NpaNxxFactory npaNxxFactory) {
        this.npaNxxFactory = npaNxxFactory;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        // Get a map from the exchange.  This is the data from Flatpack.
        Map fields = exchange.getIn().getBody(Map.class);

        // Extract the fields we know about and clean up the company field (remove double-quotes and leading/trailing whitespace)
        String state = (String) fields.get(STATE);
        String npaNxxString = (String) fields.get(NPA_NXX);
        String ocn = (String) fields.get(OCN);
        String company = ((String) fields.get(COMPANY)).replaceAll("\\\"", "").trim();
        String rateCenter = (String) fields.get(RATE_CENTER);
        String effectiveDateString = (String) fields.get(EFFECTIVE_DATE);
        String useCodeString = (String) fields.get(USE);
        String assignedDateString = (String) fields.get(ASSIGN_DATE);
        String initialGrowthString = (String) fields.get(INITIAL_GROWTH);

        // These values need special empty string treatment
        Date effectiveDate = setDate(effectiveDateString);
        Date assignedDate = setDate(assignedDateString);
        UseCode useCode = setUseCode(useCodeString);
        InitialGrowth initialGrowth = setInitialGrowth(initialGrowthString);

        // Grab the NPA and NXX from the combined NPANXX field.  There is a dash between the two values.
        int indexOfDash = npaNxxString.indexOf('-');
        int npa = Integer.parseInt(npaNxxString.substring(0, indexOfDash));
        int nxx = Integer.parseInt(npaNxxString.substring(indexOfDash + 1));

        // Create a new NpaNxx object
        NpaNxx npaNxx = npaNxxFactory.create(state, npa, nxx, ocn, company, rateCenter, effectiveDate, useCode, assignedDate, initialGrowth);

        // Set the NpaNxx object as the out body
        exchange.getOut().setBody(npaNxx);
    }

    private InitialGrowth setInitialGrowth(String initialGrowthString) {
        // Is there an initial/growth value?
        if (initialGrowthString.length() != 0) {
            // Yes, parse it
            return InitialGrowth.fromChar(initialGrowthString.charAt(0));
        }

        // No, return NULL
        return null;
    }

    private UseCode setUseCode(String useCodeString) {
        // Is there a use code value?
        if (useCodeString.length() != 0) {
            // Yes, parse it
            return UseCode.valueOf(useCodeString);
        }

        // No, return NULL
        return null;
    }

    private Date setDate(String dateString) throws ParseException {
        // Is there a date value?
        if (dateString.length() != 0) {
            // Yes, parse it
            return dateFormatter.parse(dateString);
        }

        // No, return NULL
        return null;
    }
}
