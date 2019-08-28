/*
 * @(#) CaseApi.java Copyright (c) 2019 Jala Foundation.
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
import static com.foundation.salesforce.core.utils.EndPoints.CASE_ENDPOINT;

import io.restassured.response.Response;

/**
 * CaseApi class.
 *
 * @author John Salazar Pinto.
 * @version 1.0
 */
public class CaseApi {

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
     * Constructor of CaseAPI.
     */
    protected CaseApi() {
        restClient = RestClientApi.getInstance();
        restClient.setRequest(Authentication.requestSpecification());
    }

    /**
     * Returns the instance from case API.
     *
     * @return a case API.
     */
    public static CaseApi getInstance() {
        return new CaseApi();
    }

    /**
     * Returns the response from case API.
     *
     * @return response case api
     */
    public Response getCase() {
        response = restClient.get(CASE_ENDPOINT);
        response.prettyPrint();
        return response;
    }
}
