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
import cucumber.api.java.en.When;
import io.restassured.response.Response;

import java.util.Map;

/**
 * OpportunitySteps class
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class OpportunitySteps {

    private RestClientApi restClientApi;
    private OpportunityApi opportunityApi;
    private Context context;
    private Opportunity opportunity;
    private Response response;

    /**
     * Constructor of contact com.foundation.salesforce.steps sending the context.
     *
     * @param context init the context.
     */
    public OpportunitySteps(final Context context) {
        this.context = context;
        opportunity = context.getOpportunity();
    }

    @Given("User log in into the Opportunities page")
    public void LogInIntoTheOpportunitiesPage() {
        opportunityApi.getInstance().getOpportunity();
    }


    @Given("User set up the data:")
    public void SetUpTheData(Map<String, String> inputBody) {
        opportunityApi.setContent(inputBody);
    }

    @When("User send de request post to opportunity endpoint")
    public void SendDeRequestPostToOpportunityEndpoint() {
        this.response = opportunityApi.createOpportunity();
        opportunity.setId(response.jsonPath().getString("id"));
    }

}