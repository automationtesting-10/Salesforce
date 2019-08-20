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
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.foundation.salesforce.core.utils.EndPoints.ACCOUNT_ENDPOINT;

/**
 * AccountStep class
 *
 * @author Cristian Lujan
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



    @Given("^I log in with Authorization token$")
    public void ILogInWithAuthorizationToken() {
        restClientApi.getInstance();
    }

    @Given("^I set up a GET request to Account endpoint$")
    public void iSetUpAGETRequestToAccountEndpoint() {
        accountApi.getInstance().getAccount();
    }

    //////////////////
    @Given("^I fill the request with the minimun data required$")
    public void iFillTheRequest(Map<String, String> inputFields) {
        // idAccount = accountApi.getInstance().createAccount(jsonObject);
        restClientApi = RestClientApi.getInstance();
        restClientApi.buildSpec(inputFields);
        //request = restClientApi.getRequest();
    }

    @When("^I create an Account with the name")
    public void iSendThePostWithTheName() {

        //idAccount = accountApi.getInstance().createAccount(jsonObject);
        //response = restClientApi.postAccount(ACCOUNT_ENDPOINT, jsonObject);
        response = restClientApi.post(ACCOUNT_ENDPOINT);
        response.prettyPrint();
    }
//
//    @Given("^I fill the delete request$")
//    public void iFillTheDeleteRequest() {
//        restClientApi.getInstance();
//    }
//
//    @When("^I delete the account that previously was created$")
//    public void iSendTheDelete() {
//        String endpoint = "https://na112.salesforce.com/services/data/v39.0/sobjects/Account/"+idAccount;
//        accountApi.getInstance()
//                .deleteAccount(endpoint);
//    }
}
