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
 * ValueAppender class.
 *
 * @author John Salazar Pinto.
 * @version 1.0
 */
public class ValueAppender {
    private static String suffix;
    private static String tester_name = PropertiesReader.getInstance().getConfig().getTester_name();
    private static String prefix = "Created by " + tester_name + ": ";

    /**
     * This method returns the actual date to differentiate the feature name.
     *
     * @return suffix.
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
     * @return
     */
    public static String prefix() {
        return prefix;
    }
}