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
import com.foundation.salesforce.core.utils.ValueAppender;
import com.foundation.salesforce.entities.Context;
import com.foundation.salesforce.core.restClient.Authentication;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
    private Map<String, String> leadData;

    /**
     * Initializes the class setting the context.
     *
     * @param context - Context to be set.
     */
    public LeadsSteps(Context context) {
        this.context = context;
        requestManager = RestClientApi.getInstance();
        requestManager.setRequest(Authentication.requestSpecification());
        leadData = new HashMap<>();
    }

    /**
     * Finds existing lead by Id.
     */
    @When("a user finds an existing lead by Id")
    public void aUserFindsExistingLeadById() {
        response = requestManager.get(EndPoints.LEAD_ENDPOINT + "/" + context.getLead().getId());
        response.prettyPrint();
        context.setResponse(response);
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
        context.setResponse(response);
    }

    /**
     * Retrieves a summary of all leads.
     */
    @When("a user retrieves the summary for lead")
    public void aUserRetrievesTheSummaryForLead() {
        response = requestManager.get(EndPoints.LEAD_ENDPOINT);
        response.prettyPrint();
        context.setResponse(response);
    }

    /**
     * Deletes a lead an existing by Id.
     */
    @When("a user deletes an existing lead by Id")
    public void aUserDeletesExistingLeadById() {
        response = requestManager.delete(EndPoints.LEAD_ENDPOINT + "/" + context.getLead().getId());
        response.prettyPrint();
        context.setResponse(response);
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
        context.setResponse(response);
    }

    /**
     * Sets a json object according to input map.
     *
     * @param inputFields - Input data as a Map.
     */
    @Given("a user sets json object with lead data")
    public void aUserSetsJsonObject(Map<String, String> inputFields) {
        for (Map.Entry<String, String> field : inputFields.entrySet()) {
            if (!("LastName".equals(field.getKey()))) {
                leadData.put(field.getKey(), field.getValue());
            } else {
                String lastNameConcatenated = ValueAppender.getStringWithPreffixSuffix(inputFields.get("LastName"));
                leadData.put("LastName", lastNameConcatenated);
            }
        }
        requestManager.buildSpec(leadData);
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
     * Updates existing lead by Id.
     */
    @When("the user updates existing lead")
    public void theUserUpdatesExistingLeadById() {
        response = requestManager.patch(EndPoints.LEAD_ENDPOINT + "/" + context.getLead().getId());
        response.prettyPrint();
        context.setResponse(response);
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
        context.setResponse(response);
    }

    /**
     * Verifies if Id in response is the same as the one looked for.
     */
    @And("the Id in response is the same as the one looked for")
    public void theIdInResponseIsTheSameAsTheOneLookedFor() {
        Assert.assertEquals(response.jsonPath().get("Id"), context.getLead().getId());
    }

    /**
     * Arranges a map with input data for lead creation.
     *
     * @param company - Lead's company.
     * @param lastName - Lead's last name.
     */
    @Given("a user specifies (.*) and (.*)")
    public void aUserSpecifiesCompanyAndLastName(String company, String lastName) {
        leadData.put("LastName", ValueAppender.getStringWithPreffixSuffix(lastName));
        leadData.put("Company", company);
        requestManager.buildSpec(leadData);
    }

    /**
     * Sets a json object according to input String.
     *
     * @param jsonBodyString - Input data as String.
     */
    @Given("a user provides the following json")
    public void aUserProvidesTheFollowingJson(String jsonBodyString) {
        requestManager.buildSpec(jsonBodyString);
    }

    /**
     * Adds optional lead fields to request body.
     *
     * @param fieldName - Name od the field to be added.
     * @param value - Value of the field to be added.
     */
    @And("the user adds an optional field (.*) with (.*)")
    public void addsAnOptionalFieldFieldWithValue(String fieldName, String value) {
        setLeadRequiredFields();
        leadData.put(fieldName, value);
        requestManager.buildSpec(leadData);
    }

    /**
     * Sets lead fields with required length to create or update data.
     *
     * @param field - Field to which the value is going to be set.
     * @param maximumLimit - Required length for the string generator.
     */
    @Given("a user sets a (.*) value with (.*) characters limit")
    public void aUserSetsAFieldValueWithTheMaximumCharactersLimit(String field, String maximumLimit) {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        setLeadRequiredFields();
        String pattern = "[A-Za-z0-9]{"+ Integer.parseInt(maximumLimit) + "}";
        String alphaNumericString = fakeValuesService.regexify(pattern);
        leadData.put(field, alphaNumericString);
        requestManager.buildSpec(leadData);
    }

    /**
     * Sets required fields for lead creation.
     */
    public void setLeadRequiredFields() {
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        String company = faker.company().name();
        leadData.put("Company", company);
        leadData.put("LastName", ValueAppender.getStringWithPreffixSuffix(lastName));
    }

    /**
     * Verifies is lead information is correct according to given data in creation or update.
     */
    @And("the lead information matches the given data")
    public void leadIsCreatedWithGivenData() {
        System.out.println("response in context" + context.getResponse().jsonPath());
        Map<String, String> contextResponse = context.getResponse().jsonPath().getMap("$");
        System.out.println("context response" + contextResponse.toString());
        Response validationResponse = requestManager.get(EndPoints.LEAD_ENDPOINT + "/" + contextResponse.get("id"));
        validationResponse.prettyPrint();
        SoftAssert softAssert = new SoftAssert();
        for (Map.Entry<String, String> field : leadData.entrySet()) {
            System.out.println("field key value "+ field.getValue());
            System.out.println("field in response" + validationResponse.jsonPath().get(field.getKey()));
            softAssert.assertEquals(field.getValue(), validationResponse.jsonPath().get(field.getKey()));
        }
        softAssert.assertAll();
    }
}
