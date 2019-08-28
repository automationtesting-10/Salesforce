/*
 * @(#) ValueAppender.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ValueAppender class prepare the suffix and prefix for the feature.
 *
 * @author John Salazar Pinto.
 * @version 1.0
 */

public abstract class ValueAppender {
    private static String suffix;
    private static String testerName = PropertiesReader.getInstance().getConfig().getTesterName();
    private static String prefix = "Created by " + testerName + ": ";

    /**
     * This method returns the actual date to differentiate the feature name.
     *
     * @return suffix - the actual data.
     */
    public static String suffix() {
        Date actual = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        suffix = " at " + formatter.format(actual);
        return suffix;
    }

    /**
     * This method returns the the person that runs the scenaries.
     *
     * @return prefix - the tester that run the scenaries.
     */
    public static String prefix() {
        return prefix;
    }

    /**
     * Returns the input string added to preffix and suffix.
     *
     * @param string - String to which prefix and suffix are going to be added.
     * @return Concatenated string.
     */
    public static String getStringWithPreffixSuffix(String string) {
        return prefix() + string + suffix();
    }
}
