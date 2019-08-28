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

import com.github.javafaker.Faker;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.json.JSONObject;
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
    Faker faker = new Faker();

    /**
     * Constructor of opportunity com.foundation.salesforce.steps sending the context.
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
     * @param inputBody that is input data.
     */
    @Given("User set up the data:")
    public void SetUpTheData(Map<String, String> inputBody) {
        opportunityApi.setContent(inputBody);
    }

    /**
     * Sends through a method request the data needed a opportunity.
     *
     * @param method for end point.
     */
    @When("User send request (.*) to opportunity endpoint")
    public void SendRequestMethodToOpportunityEndpoint(String method) {
        response = opportunityApi.opportunityResponse(method);
        opportunity.setId(response.jsonPath().getString("id"));
        context.setResponse(response);
        this.response.prettyPrint();
    }

    /**
     * Update an existing opportunity.
     */
    @When("User send request PATCH to new opportunity endpoint")
    public void SendRequestPATCHToNewOpportunityEndpoint() {
        response = opportunityApi.updateOpportunityById(opportunity.getId());
        context.setResponse(response);
        this.response.prettyPrint();
    }

    /**
     * Delete an existing Opportunity.
     */
    @When("User send request DELETE to new opportunity endpoint")
    public void SendRequestDELETEToNewOpportunityEndpoint() {
        response = opportunityApi.deleteOpportunityById(opportunity.getId());
        context.setResponse(response);
        this.response.prettyPrint();
    }

    /**
     * Sets a map according to input map.
     *
     * @param inputBody that is input data.
     */
    @Given("User set up all the data:")
    public void SetUpAllTheData(Map<String, String> inputBody) {
        JSONObject finalBody = new JSONObject(inputBody);
        String auxAccountId = context.getAccount().getId();
        finalBody.put("AccountId", auxAccountId);
        opportunityApi.setContent(finalBody);
    }

    /**
     * Delete an existing Opportunity.
     *
     * @param opportunityId that is input id.
     */
    @When("User makes a DELETE request for opportunity (.*)")
    public void MakeDeleteRequestForOpportunity(String opportunityId) {
        response = opportunityApi.deleteOpportunityById(opportunityId);
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Sets a map according to input map.
     *
     * @param inputContent specified as data table in gherkin feature file.
     */
    @Given("User set up Json content:")
    public void SetUpJsonContent(String inputContent) {
        opportunityApi.setContent(inputContent);
    }

    /**
     * Sets a map according to input map.
     *
     * @param lengthName that is input string data.
     * @param inputBody that is input data.
     */
    @Given("User configures the data with a random name of {int} characters:")
    public void ConfiguresTheDataWithARandomNameOfCharacters(int lengthName, Map<String, String> inputBody) {
        JSONObject finalBody = new JSONObject(inputBody);
        String name = faker.number().digits(lengthName);
        finalBody.put("Name", name);
        opportunityApi.setContent(finalBody);
    }

    /**
     * Searches an existing id.
     */
    @When("User searches for an existing opportunity")
    public void SearchesForAnExistingOpportunity() {
        response = opportunityApi.findOpportunityById(opportunity.getId());
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Sets a map according to input map.
     *
     * @param inputBody that is input data.
     */
    @Given("User configures the data with a random name:")
    public void ConfiguresTheDataWithARandomNamesAndCloseDates(Map<String, String> inputBody) {
        JSONObject finalBody = new JSONObject(inputBody);
        String name = faker.name().username();
        finalBody.put("Name", name);
        opportunityApi.setContent(finalBody);
    }
}
