/*
 * @(#) AccountApi.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.core;

import com.foundation.salesforce.core.RestClient.RestClientApi;
import io.restassured.response.Response;

import java.util.Map;

import static com.foundation.salesforce.core.utils.EndPoints.ACCOUNT_ENDPOINT;

/**
 * AccountApi class
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class AccountApi {

    /**
     * Variable for the restClient.
     */
    private RestClientApi restClient;

    /**
     * Variable for the final endpoint.
     */
    private String finalEndpoint;

    /**
     * Variable for the response.
     */
    private Response response;

    /**
     * Constructor of AccountAPI.
     */
    protected AccountApi() {
        restClient = RestClientApi.getInstance();
    }

    /**
     * Returns the instance the account API.
     *
     * @return a account API.
     */
    public static AccountApi getInstance() {
        return new AccountApi();
    }

    public Response getAccount() {
        response = restClient.get(ACCOUNT_ENDPOINT);
        response.prettyPrint();
        return response;
    }

    /**
     * Creates an account.
     *
     * @param newAccount to sent the body of the request.
     * @return the id of account created.
     */
    public String createAccount(final Map<String, String> newAccount) {
        finalEndpoint = ACCOUNT_ENDPOINT;
        response = restClient.post(finalEndpoint);
        response.prettyPrint();
        return response.body().jsonPath().getString("id");
    }
}