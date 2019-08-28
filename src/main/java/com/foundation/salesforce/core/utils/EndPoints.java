/*
 * @(#) EndPoints.java Copyright (c) 2019 Jala Foundation.
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

/**
 * EndPoints class.
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public final class EndPoints {
    public static final String ACCOUNT_ENDPOINT = "/services/data/v39.0/sobjects/Account";
    public static final String OPPORTUNITY_ENDPOINT = "/services/data/v46.0/sobjects/Opportunity";
    public static final String TASK_ENDPOINT = "/services/data/v46.0/sobjects/Task";
    public static final String CONTACT_ENDPOINT = "/services/data/v39.0/sobjects/Contact";
    public static final String LEAD_ENDPOINT = "/services/data/v39.0/sobjects/lead";
    public static final String CASE_ENDPOINT = "/services/data/v39.0/sobjects/Case";

    /**
     * Constructor protected.
     */
    private EndPoints() {
    }
}
