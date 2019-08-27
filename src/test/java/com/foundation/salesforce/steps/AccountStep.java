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
import com.foundation.salesforce.core.restClient.RestClientApi;
import com.foundation.salesforce.core.utils.EndPoints;
import com.foundation.salesforce.core.utils.ResponseValidation;
import com.foundation.salesforce.entities.Context;
import static com.foundation.salesforce.core.utils.EndPoints.ACCOUNT_ENDPOINT;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Map;

/**
 * AccountStep class with the steps for the account feature.
 *
 * @author John Salazar Pinto
 * @version 1.0
 */
public class AccountStep {
    private RestClientApi restClientApi;
    private AccountApi accountApi;
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
    public AccountStep(Context context) {
        this.context = context;
    }

    /**
     * I do the authentication.
     */
    @Given("^I log in with Authorization token$")
    public void ILogInWithAuthorizationToken() {
        restClientApi.getInstance();
    }

    /**
     * Get the account.
     */
    @When("^I get all accounts created$")
    public void iSetUpAGETRequestToAccountEndpoint() {
        response = accountApi.getInstance().getAccount();
        context.setResponse(response);
    }

    /**
     * The body is fill with the minimun data required.
     *
     * @param inputFields
     */
    @Given("^I fill the request with the data required$")
    public void iFillTheRequest(Map<String, String> inputFields) {
        accountApi.getInstance().getAccount();
        restClientApi = RestClientApi.getInstance();
        restClientApi.buildSpec(inputFields);
    }

    /**
     * Creates the account.
     */
    @When("^I create an Account with the name")
    public void iSendThePostWithTheName() {
        response = restClientApi.post(ACCOUNT_ENDPOINT);
        response.prettyPrint();
        context.setResponse(response);
    }

    /**
     * Deletes an account by id.
     */
    @When("^I delete an account that previusly was created$")
    public void iFillTheDeleteRequest() {
        restClientApi = RestClientApi.getInstance();
        response = restClientApi.delete(ACCOUNT_ENDPOINT + "/" + context.getAccount().getId());
        response.prettyPrint();
    }

    /**
     * Verifies response's status code.
     *
     * @param statusCode Expected status for delete account.
     */
    @Then("the status code is a number {int}")
    public void theStatusCodeIs(int statusCode) {
        json = response.then().statusCode(statusCode);
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    /**
     * Sets a json object according to input map.
     *
     * @param inputFields - Input data.
     */
    @Given("a user sets json object for the modify account")
    public void aUserSetsJsonObjectWithRequiredFields(Map<String, String> inputFields) {
        restClientApi = RestClientApi.getInstance();
        restClientApi.buildSpec(inputFields);
        context.setResponse(response);
    }

    /**
     * Updates existing account by Id.
     */
    @When("the user updates existing account by Id")
    public void theUserUpdatesExistingLeadById() {
        response = restClientApi.patch(ACCOUNT_ENDPOINT + "/" + context.getAccount().getId());
        response.prettyPrint();
    }

    /**
     * Schema account validation.
     */
    @And("schema {string} is valid")
    public void schemaIsValid(String schemaTypeName) {
        boolean actual = ResponseValidation.getInstance().matchesJsonSchema(schemaTypeName, this.response);
        Assert.assertTrue(actual);
    }

    /**
     * Updates existing lead by Id.
     */
    @When("the user creates the account")
    public void theUserCreatesTheAccount() {
        response = restClientApi.post(ACCOUNT_ENDPOINT);
        response.prettyPrint();
        context.setResponse(response);
    }

    @When("a user finds an existing account by Id")
    public void aUserFindsAnExistingAccountById() {
        restClientApi = RestClientApi.getInstance();
        response = restClientApi.get(ACCOUNT_ENDPOINT + "/" + context.getAccount().getId());
        response.prettyPrint();
    }

    @When("a user finds a account by Id {string}")
    public void aUserFindsAAccountById(String leadId) {
        restClientApi = RestClientApi.getInstance();
        response = restClientApi.get(ACCOUNT_ENDPOINT + "/" + leadId);
        response.prettyPrint();
    }

    @When("a user deletes a account by Id {string}")
    public void aUserDeletesAAccountById(String leadId) {
        restClientApi = RestClientApi.getInstance();
        response = restClientApi.delete(ACCOUNT_ENDPOINT + "/" + leadId);
        response.prettyPrint();
    }

    @And("response contains the following message")
    public void responseContainsTheFollowing(Map<String, String> bodyFields) {
        SoftAssert softAssert = new SoftAssert();
        Map<String, String> responseFirstElement = response.jsonPath().getMap("[0]");
        for (Map.Entry<String, String> field : bodyFields.entrySet()) {
            softAssert.assertEquals(responseFirstElement.get(field.getKey()), field.getValue());
        }
        softAssert.assertAll();
    }
}
