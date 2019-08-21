package com.foundation.salesforce.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ValueAppender {
    private static String prefix = "Test ";
    private static String suffix;

    public static String suffix() {
        Date actual = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        suffix = " at " + formatter.format(actual);
        return suffix;
    }

    public static String prefix() {
        return prefix;
    }
}