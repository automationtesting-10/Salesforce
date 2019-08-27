/*
 * @(#) AccountStep.java Copyright (c) 2019 Jala Foundation.
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

import com.foundation.salesforce.core.api.AccountApi;
import com.foundation.salesforce.core.api.CaseApi;
import com.foundation.salesforce.core.restClient.RestClientApi;
import com.foundation.salesforce.core.utils.ResponseValidation;
import com.foundation.salesforce.entities.Context;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.Map;

import static com.foundation.salesforce.core.utils.EndPoints.CASE_ENDPOINT;

/**
 * AccountStep class with the steps for the account feature.
 *
 * @author John Salazar Pinto
 * @version 1.0
 */
public class CaseStep {
    private RestClientApi restClientApi;
    private AccountApi accountApi;
    private CaseApi caseApi;
    private String idAccount;
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    private JSONObject bodyData;
    private Context context;

    /**
     * Initializes the class setting the context.
     *
     * @param context - Context to be set.
     */
    public CaseStep(Context context) {
        this.context = context;
    }


    @When("I create an case with the name")
    public void iCreateAnCaseWithTheName() {
        response = restClientApi.post(CASE_ENDPOINT);
        response.prettyPrint();
        context.setResponse(response);
    }

    @Given("I fill the request case with the data required")
    public void iFillTheRequest(Map<String, String> inputFields) {
        caseApi.getInstance().getCase();
        //accountApi.getInstance().getAccount();
        restClientApi = RestClientApi.getInstance();
        restClientApi.buildSpec(inputFields);
    }

    /**
     * Verifies response's status code.
     *
     * @param statusCode Expected status for delete case.
     */
    @Then("the status code is a numbert {int}")
    public void theStatusCodeIs(int statusCode) {
        json = response.then().statusCode(statusCode);
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    /**
     * Schema case validation.
     */
    @And("schema case {string} is valid")
    public void schemaIsValid(String schemaTypeName) {
        boolean actual = ResponseValidation.getInstance().matchesJsonSchema(schemaTypeName, this.response);
        Assert.assertTrue(actual);
    }

    @Then("the status code case is a number {int}")
    public void theStatusCodeCaseIsANumber(int statusCode) {
        json = response.then().statusCode(statusCode);
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @When("I delete an case that previusly was created")
    public void iDeleteAnCaseThatPreviuslyWasCreated() {
        restClientApi = RestClientApi.getInstance();
        response = restClientApi.delete(CASE_ENDPOINT + "/" + context.getCase().getId());
        response.prettyPrint();
    }

    @Given("a user sets json object for the modify case")
    public void aUserSetsJsonObjectWithRequiredFields(Map<String, String> inputFields) {
        restClientApi = RestClientApi.getInstance();
        restClientApi.buildSpec(inputFields);
        context.setResponse(response);
    }

    @When("the user updates existing case by Id")
    public void theUserUpdatesExistingCaseById() {
        response = restClientApi.patch(CASE_ENDPOINT + "/" + context.getCase().getId());
        response.prettyPrint();
    }

    @When("I get all case created")
    public void iGetAllCaseCreated() {
        response = accountApi.getInstance().getAccount();
        context.setResponse(response);
    }

    @When("a user finds an existing case by Id")
    public void aUserFindsAnExistingCaseById() {
        restClientApi = RestClientApi.getInstance();
        response = restClientApi.get(CASE_ENDPOINT + "/" + context.getCase().getId());
        response.prettyPrint();
    }

    @When("a user finds a case by Id {string}")
    public void aUserFindsACaseById(String leadId) {
        restClientApi = RestClientApi.getInstance();
        response = restClientApi.get(CASE_ENDPOINT + "/" + leadId);
        response.prettyPrint();
    }
}
