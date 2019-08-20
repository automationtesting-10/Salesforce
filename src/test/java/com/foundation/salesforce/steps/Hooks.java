/*
 * @(#) Hooks.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.steps;

import com.foundation.salesforce.entities.Context;
import cucumber.api.java.After;

/**
 * Hooks class contains before and after actions for Lead endpoint.
 *
 * @author Melissa Roman
 * @version 1.0
 **/
public class Hooks {
    private Context context;

    /**
     * Initializes the class setting the context.
     *
     * @param context - Context to be set.
     */
    public Hooks(Context context) {
        this.context = context;
    }

    /**
     * Actions performed after LeadCreation scenario.
     */
    @After("@LeadCreation")
    public void afterScenario() {
        System.out.println("This will run after the creation Scenario"
                + context.getLead().getId());
    }
}
