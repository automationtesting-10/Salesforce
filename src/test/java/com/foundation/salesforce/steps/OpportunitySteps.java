/*
 * @(#) OpportunitySteps.java Copyright (c) 2019 Jala Foundation.
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

import com.foundation.salesforce.core.api.OpportunityApi;
import com.foundation.salesforce.core.restClient.RestClientApi;
import com.foundation.salesforce.entities.Context;
import com.foundation.salesforce.entities.Opportunity;
import cucumber.api.java.en.Given;

/**
 * OpportunitySteps class
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class OpportunitySteps {

    private RestClientApi restClientApi;
    private OpportunityApi opportunityApi;

    /**
     * Variable for the context.
     */
    private Context context;

    private Opportunity opportunity;

    /**
     * Constructor of contact com.foundation.salesforce.steps sending the context.
     *
     * @param context init the context.
     */
    public OpportunitySteps(final Context context) {
        this.context = context;
        opportunity = context.getOpportunity();
    }

    @Given("^I set up a GET request to Opportunities endpoint$")
    public void iSetUpAGETRequestToOpportunitiesEndpoint() {
        opportunityApi.getInstance().getOpportunity();
    }
}