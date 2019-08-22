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

package com.foundation.salesforce.steps.steps;

import com.foundation.salesforce.core.api.AccountApi;
import com.foundation.salesforce.core.restClient.RestClientApi;
import com.foundation.salesforce.core.utils.ResponseValidation;
import com.foundation.salesforce.entities.Context;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

import static com.foundation.salesforce.core.utils.EndPoints.ACCOUNT_ENDPOINT;

/**
 * AccountStep class
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

    @Given("^I log in with Authorization token$")
    public void ILogInWithAuthorizationToken() {
        restClientApi.getInstance();
    }

    @Given("^I set up a GET request to Account endpoint$")
    public void iSetUpAGETRequestToAccountEndpoint() {
        accountApi.getInstance().getAccount();

    }

    @Given("^I fill the request with the minimun data required$")
    public void iFillTheRequest(Map<String, String> inputFields) {
        restClientApi = RestClientApi.getInstance();
        restClientApi.buildSpec(inputFields);

    }

    @When("^I create an Account with the name")
    public void iSendThePostWithTheName() {
        response = restClientApi.post(ACCOUNT_ENDPOINT);
        response.prettyPrint();
        context.setResponse(response);
    }

    @When("^I fill the delete request$")
    public void iFillTheDeleteRequest() {
        restClientApi = RestClientApi.getInstance();
        response = restClientApi.delete(ACCOUNT_ENDPOINT + "/" + context.getAccount().getId());
        response.prettyPrint();
    }

    @Then("I delete the account that previously was created is {int}")
    public void iDeleteTheAccountThatPreviouslyWasCreatedIs(int statusCode) {
        json = response.then().statusCode(statusCode);
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("the status code is {int}")
    public void theStatusCodeIs(int statusCode) {
        json = response.then().statusCode(statusCode);
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @And("{string} is valid")
    public void accountCreationResponseIsValid(String schemaTypeName) {
        boolean actual = ResponseValidation.getInstance().matchesJsonSchema(schemaTypeName, this.response);
        Assert.assertTrue(actual);
    }
}
