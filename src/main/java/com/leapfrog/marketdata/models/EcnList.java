package com.leapfrog.marketdata.models;

import java.util.List;

public final class EcnList {
    public static final String UBS = "UBS";
    public static final String BARCLAYS = "Barclays";
    public static final String SWISSQUOTE = "Swissquote";
    public static final String FXCM = "FXCM";

    public static final List<String> ALL = List.of(UBS, BARCLAYS, SWISSQUOTE, FXCM);
}
