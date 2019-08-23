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

package com.foundation.salesforce.core.api;

import com.foundation.salesforce.core.restClient.Authentication;
import com.foundation.salesforce.core.restClient.RestClientApi;
import io.restassured.response.Response;

import static com.foundation.salesforce.core.utils.EndPoints.ACCOUNT_ENDPOINT;

/**
 * AccountApi class.
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class AccountApi {

    /**
     * Variable for the rest client.
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
            restClient.setRequest(Authentication.requestSpecification());
    }

    /**
     * Returns the instance from account API.
     *
     * @return a account API.
     */
    public static AccountApi getInstance() {
        return new AccountApi();
    }

    /**
     * Returns the response from account API.
     * @return response api
     */
    public Response getAccount() {
        response = restClient.get(ACCOUNT_ENDPOINT);
        response.prettyPrint();
        return response;
    }
}
