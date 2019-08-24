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
import com.foundation.salesforce.core.utils.ResponseValidation;
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

    /**
     * Sets a map according to input map.
     *
     * @param inputBody that is input data
     */
    @Given("User set up the data:")
    public void SetUpTheData(Map<String, String> inputBody) {
        opportunityApi.setContent(inputBody);
    }

    /**
     * Checks the resulting status code.
     *
     * @param statusCode of integer type
     */
    @Then("^User get a \"([^\"]*)\" status code as response$")
    public void GetAStatusCodeAsResponse(int statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    /**
     * Sends through a method request the data needed a opportunity.
     *
     * @param method for end point
     */
    @When("User send request (.*) to opportunity endpoint")
    public void SendRequestMethodToOpportunityEndpoint(String method) {
        response = opportunityApi.opportunityResponse(method);
        opportunity.setId(response.jsonPath().getString("id"));
        this.response.prettyPrint();
    }

    /**
     * Checks the response obtained after creating a Opportunity.
     *
     * @param responseOpportunity a RestAssured response structure.
     */
    @And("The message of the response is:")
    public void MessageOfTheResponseIs(Map<String, String> responseOpportunity) {
        for (Map.Entry<String, String> field : responseOpportunity.entrySet()) {
            Assert.assertEquals(response.jsonPath().get(field.getKey()).toString(), field.getValue());
        }
    }

    /**
     * Update an existing Task.
     */
    @When("User send request PATCH to new opportunity endpoint")
    public void SendRequestPATCHToNewOpportunityEndpoint() {
        response = opportunityApi.updateOpportunityById(opportunity.getId());
        this.response.prettyPrint();
    }

    /**
     * Delete an existing Task.
     */
    @When("User send request DELETE to new opportunity endpoint")
    public void SendRequestDELETEToNewOpportunityEndpoint() {
        this.response = opportunityApi.deleteOpportunityById(opportunity.getId());
        this.response.prettyPrint();
    }

    /**
     *
     * @param schemaTypeName
     */
    @And("User verify response in the (.*)")
    public void userVerifyResponseInTheOpportunityScheme(String schemaTypeName) {
        boolean actual = ResponseValidation.getInstance().matchesJsonSchema(schemaTypeName, this.response);
        Assert.assertTrue(actual);
    }

}
