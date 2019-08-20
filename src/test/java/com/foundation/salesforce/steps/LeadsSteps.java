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
import com.foundation.salesforce.entities.Lead;
import com.foundation.salesforce.core.restClient.Authentication;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

/**
 * LeadsSteps class contains steps for Lead endpoint.
 *
 * @author Melissa Roman
 * @version 1.0
 **/
public class LeadsSteps {
    private Context context;
    private RestClientApi requestManager;
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    private JSONObject bodyData;
    static private String createdLeadId;

    /**
     * Initializes the class setting the context.
     *
     * @param context - Context to be set.
     */
    public LeadsSteps(Context context) {
        this.context = context;
        requestManager = RestClientApi.getInstance();
    }

    /**
     * Allows to get created lead Id.
     *
     * @return Created lead Id.
     */
    static String getCreatedLeadId() {
        return createdLeadId;
    }

    /**
     * Retrieves the lead info by Id.
     *
     * @param leadId - Lead's Id.
     */
    @When("a user retrieves the lead by Id (.*)")
    public void aUserRetrievesTheLeadById(String leadId) {
        request = Authentication.requestSpecification();
        response = given().spec(request).get(EndPoints.LEAD_ENDPOINT + "/" + leadId);
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
    }

    /**
     * Verifies response's json body.
     *
     * @param responseFields - Expected fields and values.
     */
    @And("response includes the following")
    public void responseIncludesTheFollowing(Map<String, String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if (StringUtils.isNumeric(field.getValue())) {
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            } else {
                if (("true".equals(field.getValue())) || ("false".equals(field.getValue()))) {
                    json.body(field.getKey(), equalTo(Boolean.parseBoolean(field.getValue())));
                } else {
                    json.body(field.getKey(), equalTo(field.getValue()));
                }
            }
        }
    }

    /**
     * Retrieves a summary of all leads.
     */
    @When("a user retrieves the summary for lead")
    public void aUserRetrievesTheSummaryForLead() {
        request = Authentication.requestSpecification();
        response = given().spec(request).get(EndPoints.LEAD_ENDPOINT);
        response.prettyPrint();
    }

    /**
     * Deletes a lead by Id.
     *
     * @param leadId - Lead's Id.
     */
    @When("a user deletes a lead by Id (.*)")
    public void aUserDeletesALeadById(String leadId) {
        request = Authentication.requestSpecification();
        response = given().spec(request).delete(EndPoints.LEAD_ENDPOINT + "/" + leadId);
        response.prettyPrint();
    }

    /**
     * Sets a json object according to input map.
     *
     * @param inputFields - Input data.
     */
    @Given("a user sets json object")
    public void aUserSetsJsonObjectWithRequiredFields(Map<String, String> inputFields) {
        bodyData = new JSONObject();
        for (Map.Entry<String, String> field : inputFields.entrySet()) {
            bodyData.put(field.getKey(), field.getValue());
        }
    }

    /**
     * Creates a lead.
     */
    @When("the user creates the lead")
    public void theUserCreatesTheLead() {
        request = Authentication.requestSpecification();
        request.contentType("application/json").body(bodyData.toString());
        response = given().spec(request).post(EndPoints.LEAD_ENDPOINT);
        response.prettyPrint();
    }

    /**
     * Verifies response's array of json objects.
     *
     * @param responseFields - Expected fields and values.
     */
    @And("response contains the following")
    public void responseContainsTheFollowing(Map<String, String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if (StringUtils.isNumeric(field.getValue())) {
                json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
            } else {
                if (("true".equals(field.getValue())) || ("false".equals(field.getValue()))) {
                    json.body(field.getKey(), containsInAnyOrder(Boolean.parseBoolean(field.getValue())));
                } else {
                    json.body(field.getKey(), containsInAnyOrder(field.getValue()));
                }
            }
        }
    }

    /**
     * Saves the created lead.
     */
    @And("the created lead in saved")
    public void theResponseInSaved() {
        Map<String, String> creationResponse = response.jsonPath().getMap("$");
        context.getLead().setId(creationResponse.get("id"));
        createdLeadId = creationResponse.get("id");
    }

    /**
     * Updates existing lead by Id.
     *
     * @param leadId - Id from lead that is going to be updated.
     */
    @When("the user updates the lead by Id (.*)")
    public void theUserUpdatesTheLeadById(String leadId) {
        request = Authentication.requestSpecification();
        request.contentType("application/json").body(bodyData.toString());
        response = given().spec(request).patch(EndPoints.LEAD_ENDPOINT + "/" + leadId);
        response.prettyPrint();
    }
}
