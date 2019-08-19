/*
 * Salesforce
 *
 * Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguín, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.core.api;

import com.foundation.salesforce.core.restClient.RestClientApi;
import io.restassured.response.Response;

import java.util.Map;

import static com.foundation.salesforce.core.utils.EndPoints.OPPORTUNITY_ENDPOINT;

/**
 * OpportunityApi class
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class OpportunityApi {

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
    protected OpportunityApi() {
        restClient = RestClientApi.getInstance();
    }

    /**
     * Returns the instance the opportunity API.
     *
     * @return a account API.
     */
    public static OpportunityApi getInstance() {
        return new OpportunityApi();
    }

    /**
     * Return the response after requesting an opportunities.
     *
     * @return the response.
     */
    public Response getOpportunity() {
        response = restClient.get(OPPORTUNITY_ENDPOINT);
        response.prettyPrint();
        return response;
    }

    /**
     * Creates an account.
     *
     * @param newOpportunity to sent the body of the request.
     * @return the id of account created.
     */
    public String createOpportunity(final Map<String, String> newOpportunity) {
        finalEndpoint = OPPORTUNITY_ENDPOINT;
        response = restClient.post(finalEndpoint);
        response.prettyPrint();
        return response.body().jsonPath().getString("id");
    }
}