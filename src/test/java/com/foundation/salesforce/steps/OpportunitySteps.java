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
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

/**
 * OpportunitySteps class.
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
        opportunityApi = OpportunityApi.getInstance();
    }

    @Given("User set up the data:")
    public void SetUpTheData(Map<String, String> inputBody) {
        opportunityApi.setContent(inputBody);
    }

    @When("User send de request post to opportunity endpoint")
    public void SendDeRequestPostToOpportunityEndpoint() {
        this.response = opportunityApi.createOpportunity();
        opportunity.setId(response.jsonPath().getString("id"));
        this.response.prettyPrint();
    }

    @Then("^User get a \"([^\"]*)\" status code as response$")
    public void userGetAStatusCodeAsResponse(int statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    @And("^The response has been success true$")
    public void theResponseHasBeenSuccessTrue() {

    }
}
