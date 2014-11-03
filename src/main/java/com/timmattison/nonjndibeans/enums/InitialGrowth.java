package com.timmattison.nonjndibeans.enums;

/**
 * Created by timmattison on 11/4/14.
 */
public enum InitialGrowth {
    Initial("Initial is the first geographic NXX code assigned at a unique switching entity or point of interconnection for a service provider."),
    Growth("Growth is a code assigned to a switching entity or point of interconnection subsequent to the assignment of the first code, for the same purpose as a code that was previously assigned for a service provider to the same switching entity or point of interconnection.");

    public String getDescription() {
        return description;
    }

    private final String description;

    InitialGrowth(String description) {
        this.description = description;
    }

    public static InitialGrowth fromChar(char igChar) {
        if (igChar == 'I') {
            return Initial;
        } else if (igChar == 'G') {
            return Growth;
        } else {
            throw new UnsupportedOperationException("Unrecognized IG value [" + igChar + "]");
        }
    }
}
