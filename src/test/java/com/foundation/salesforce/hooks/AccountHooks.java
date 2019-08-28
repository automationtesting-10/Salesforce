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

package com.foundation.salesforce.hooks;

import com.foundation.salesforce.core.restClient.Authentication;
import com.foundation.salesforce.core.restClient.RestClientApi;
import com.foundation.salesforce.core.utils.EndPoints;
import com.foundation.salesforce.core.utils.ValueAppender;
import com.foundation.salesforce.entities.Context;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.response.Response;

import java.util.Map;

/**
 * AccountHooks class contains before and after actions for account endpoint.
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
     * Creates an account before scenarios.
     */
    @Before("@DeleteAccount, @FindAccount, @UpdateAccount")
    public void createAccountBefore() {
        String name = ValueAppender.prefix() + "Account" + ValueAppender.suffix();
        String key = "Name";
        String json = String.format("{\"%s\": \"%s\"}", key, name);
        restClientApi.buildSpec(json);
        Response response = restClientApi.post(EndPoints.ACCOUNT_ENDPOINT);
        Map<String, String> creationResponse = response.jsonPath().getMap("$");
        context.getAccount().setId(creationResponse.get("id"));
    }

    /**
     * Deletes created accounts after scenarios.
     */
    @After(value = "@CreateAccount, @FindAccount, @UpdateAccount", order = 0)
    public void deleteAccountAfterCreation() {
        restClientApi.delete(EndPoints.ACCOUNT_ENDPOINT + "/" + context.getAccount().getId());
    }

    /**
     * Sets context's account id after AccountCreation scenario.
     */
    @After(value = "@CreateAccount", order = 1)
    public void saveAccountfterCreation() {
        Map<String, String> creationResponse = context.getResponse().jsonPath().getMap("$");
        context.getAccount().setId(creationResponse.get("id"));
    }
}
