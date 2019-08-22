/*
 * @(#) LeadsSteps.java Copyright (c) 2019 Jala Foundation.
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

import com.foundation.salesforce.core.restClient.RestClientApi;
import com.foundation.salesforce.core.utils.EndPoints;
import com.foundation.salesforce.entities.Context;
import com.foundation.salesforce.core.restClient.Authentication;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * LeadsSteps class contains steps for lead endpoint.
 *
 * @author Melissa Roman
 * @version 1.0
 **/
public class LeadsSteps {
    private Context context;
    private RestClientApi requestManager;
    private Response response;
    private ValidatableResponse json;

    /**
     * Initializes the class setting the context.
     *
     * @param context - Context to be set.
     */
    public LeadsSteps(Context context) {
        this.context = context;
        requestManager = RestClientApi.getInstance();
        requestManager.setRequest(Authentication.requestSpecification());
    }

    /**
     * Finds existing lead by Id.
     */
    @When("a user finds an existing lead by Id")
    public void aUserFindsExistingLeadById() {
        response = requestManager.get(EndPoints.LEAD_ENDPOINT + "/" + context.getLead().getId());
        response.prettyPrint();
    }

    /**
     * Finds the lead by Id.
     *
     * @param leadId - lead's Id.
     */
    @When("a user finds a lead by Id (.*)")
    public void aUserFindsLeadById(String leadId) {
        response = requestManager.get(EndPoints.LEAD_ENDPOINT + "/" + leadId);
        response.prettyPrint();
    }

    /**
     * Verifies response's status code.
     *
     * @param statusCode - Expected status code.
     */
    @Then("the status code is {int}")
    public void theStatusCodeIs(int statusCode) {
        json = response.then().statusCode(statusCode);
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    /**
     * Verifies response's json body.
     *
     * @param bodyFields - Expected fields and values.
     */
    @And("response includes the following")
    public void responseIncludesTheFollowing(Map<String, String> bodyFields) {
        SoftAssert softAssert = new SoftAssert();
        for (Map.Entry<String, String> field : bodyFields.entrySet()) {
            softAssert.assertEquals(response.jsonPath().get(field.getKey()).toString(), field.getValue());
        }
        softAssert.assertAll();
    }

    /**
     * Retrieves a summary of all leads.
     */
    @When("a user retrieves the summary for lead")
    public void aUserRetrievesTheSummaryForLead() {
        response = requestManager.get(EndPoints.LEAD_ENDPOINT);
        response.prettyPrint();
    }

    /**
     * Deletes a lead an existing by Id.
     */
    @When("a user deletes an existing lead by Id")
    public void aUserDeletesExistingLeadById() {
        response = requestManager.delete(EndPoints.LEAD_ENDPOINT + "/" + context.getLead().getId());
        response.prettyPrint();
    }

    /**
     * Deletes a lead by Id.
     *
     * @param leadId - lead's Id.
     */
    @When("a user deletes a lead by Id (.*)")
    public void aUserDeletesALeadById(String leadId) {
        response = requestManager.delete(EndPoints.LEAD_ENDPOINT + "/" + leadId);
        response.prettyPrint();
    }

    /**
     * Sets a json object according to input map.
     *
     * @param inputFields - Input data.
     */
    @Given("a user sets json object")
    public void aUserSetsJsonObjectWithRequiredFields(Map<String, String> inputFields) {
        requestManager.buildSpec(inputFields);
    }

    /**
     * Creates a lead.
     */
    @When("the user creates the lead")
    public void theUserCreatesTheLead() {
        response = requestManager.post(EndPoints.LEAD_ENDPOINT);
        response.prettyPrint();
        context.setResponse(response);
    }

    /**
     * Verifies response's body array of json objects.
     *
     * @param bodyFields - Expected fields and values.
     */
    @And("response contains the following")
    public void responseContainsTheFollowing(Map<String, String> bodyFields) {
        SoftAssert softAssert = new SoftAssert();
        Map<String, String> responseFirstElement = response.jsonPath().getMap("[0]");
        for (Map.Entry<String, String> field : bodyFields.entrySet()) {
            softAssert.assertEquals(responseFirstElement.get(field.getKey()), field.getValue());
        }
        softAssert.assertAll();
    }

    /**
     * Updates existing lead by Id.
     */
    @When("the user updates existing lead by Id")
    public void theUserUpdatesExistingLeadById() {
        response = requestManager.patch(EndPoints.LEAD_ENDPOINT + "/" + context.getLead().getId());
        response.prettyPrint();
    }

    /**
     * Updates lead by Id.
     *
     * @param leadId - Id from lead that is going to be updated.
     */
    @When("the user updates lead by Id (.*)")
    public void theUserUpdatesLeadById(String leadId) {
        response = requestManager.patch(EndPoints.LEAD_ENDPOINT + "/" + leadId);
        response.prettyPrint();
    }

    /**
     * Verifies response headers.
     *
     * @param headerFields - Expected headers and values.
     */
    @And("headers include the following")
    public void headersIncludeTheFollowing(Map<String, String> headerFields) {
        SoftAssert softAssert = new SoftAssert();
        for (Map.Entry<String, String> field : headerFields.entrySet()) {
            softAssert.assertEquals(response.getHeader(field.getKey()), field.getValue());
        }
        softAssert.assertAll();
    }
}
