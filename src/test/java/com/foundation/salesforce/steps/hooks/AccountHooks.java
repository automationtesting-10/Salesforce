/*
 * @(#) AccountHooks.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */
package com.foundation.salesforce.steps.hooks;

import com.foundation.salesforce.core.restClient.Authentication;
import com.foundation.salesforce.core.restClient.RestClientApi;
import com.foundation.salesforce.core.utils.EndPoints;
import com.foundation.salesforce.core.utils.ValueAppender;
import com.foundation.salesforce.entities.Context;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import io.restassured.response.Response;

import java.util.Map;

/**
 * LeadHooks class contains before and after actions for lead endpoint.
 *
 * @author John Salazar Pinto
 * @version 1.0
 **/
public class AccountHooks {
    private Context context;
    private RestClientApi restClientApi;

    /**
     * Initializes the class setting the context.
     *
     * @param context - Context to be set.
     */
    public AccountHooks(Context context) {
        this.context = context;
        restClientApi = RestClientApi.getInstance();
        restClientApi.setRequest(Authentication.requestSpecification());
    }

    /**
     * Deletes created account after tagged scenarios.
     */
    @After ("@AccountCreation, @FindAccount, @UpdateAccount")
    public void deleteAccountAfterCreation() {
        restClientApi.delete(EndPoints.ACCOUNT_ENDPOINT + "/" + context.getAccount().getId());
        System.out.println(context.getAccount().getId()+"this is the Id contenx");
    }

    /**
     * Creates a account before tagged scenarios.
     */
    @Before("@DeleteAccount, @FindAccount, @UpdateAccount")
    public void createAccountBefore() {
        String name = ValueAppender.prefix() + "Account1" + ValueAppender.suffix();
        restClientApi.buildSpec("{" +
                "\"Name\": \"" + name + "\"" +
                "}");
        Response response = restClientApi.post(EndPoints.ACCOUNT_ENDPOINT);
        Map<String, String> creationResponse = response.jsonPath().getMap("$");
        context.getAccount().setId(creationResponse.get("id"));
    }

    /**
     * Sets context's account id after AccountCreation scenario.
     */
    @After ("@AccountCreation")
    public void saveAccountfterCreation() {
        Map<String, String> creationResponse = context.getResponse().jsonPath().getMap("$");
        context.getAccount().setId(creationResponse.get("id"));
    }
}
