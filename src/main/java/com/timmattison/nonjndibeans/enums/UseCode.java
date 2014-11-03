package com.timmattison.nonjndibeans.enums;

/**
 * Created by timmattison on 11/4/14.
 */
public enum UseCode {
    VC("VC indicates codes that are vacant; they are available for assignment to a service provider."),
    AS("AS indicates codes that are assigned to a service provider."),
    PR("PR indicates codes that are protected for a split; that is, these codes are assigned in the old or the new NPA and can not be assigned in the other NPA until after the end of permissive dialing."),
    RV("RV indicates codes that have been reserved by a service provider. The identity of the service provider is considered proprietary information and will not be shown."),
    UA("UA indicates codes that are unavailable for assignment. These codes include, but are not limited to, test and special use codes (e.g., 958, 959, 555, time), N11 and other unique codes (e.g., 976, 950), codes set aside for pooling, and codes with special dialing arrangements (e.g., 7-digit dialing cross NPA boundary).");

    public String getDescription() {
        return description;
    }

    private final String description;

    UseCode(String description) {
        this.description = description;
    }
}
