/*
 * @(#) OpportunityApi.java Copyright (c) 2019 Jala Foundation.
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
import org.json.JSONObject;

import java.util.Map;

import static com.foundation.salesforce.core.utils.EndPoints.OPPORTUNITY_ENDPOINT;

/**
 * OpportunityApi class.
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class OpportunityApi {
    private RestClientApi restClient;
    private String finalEndpoint;
    private Response response;

    /**
     * Constructor of AccountAPI.
     */
    protected OpportunityApi() {
        restClient = RestClientApi.getInstance();
        restClient.setRequest(Authentication.requestSpecification());
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
     * Add a deserialized json content to the body of 'request' Requestpecification attribute of this class.
     *
     * @param opportunityBody a flat deserialized String containing the key/value data to be provided to a POST or
     *      PATCH request.
     */
    public void setContent(String opportunityBody) {
        restClient.buildSpec(opportunityBody);
    }

    /**
     * Add a json content to the body of 'request' Requestpecification attribute contained in a Map format.
     *
     * @param opportunityBody a Map structure containing the key/value data to be provided to a POST or PATCH request.
     */
    public void setContent(Map opportunityBody) {
        restClient.buildSpec(opportunityBody);
    }

    /**
     * Adds content to the body of 'request' Requestpecification attribute contained within a JSONObject.
     *
     * @param opportunityBody a JSONObject containing the key/value data to be provided to a POST or PATCH request.
     */
    public void setContent(JSONObject opportunityBody) {
        restClient.buildSpec(opportunityBody);
    }

    /**
     * Return the response after requesting an opportunities.
     *
     * @return the RestAssured response.
     */
    public Response getOpportunity() {
        response = restClient.get(OPPORTUNITY_ENDPOINT);
        response.prettyPrint();
        return response;
    }

    /**
     * Returns a RestAssured Response as a result of a successful POST request.
     *
     * @return a RestAssured Response.
     */
    public Response createOpportunity() {
        response = restClient.post(OPPORTUNITY_ENDPOINT);
        response.prettyPrint();
        return response;
    }

    /**
     * Delete a previously created Task specified by its id.
     *
     * @param id uniquely identifies a given Task.
     * @return a RestAssured Response structure as a result of a successful DELETE request.
     */
    public Response deleteOpportunityById(String id) {
        finalEndpoint = OPPORTUNITY_ENDPOINT.concat("/".concat(id));
        response = restClient.delete(finalEndpoint);
        return response;
    }

//    public boolean recorridoJson(Map<String, String> response) {
//        for (Map.Entry<String, String> field : response.entrySet()) {
//            Assert.assertEquals(this.response.jsonPath().get(field.getKey()).toString(), field.getValue());
//        }
//    }
}
