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
     * Constructor of OpportunityAPI.
     */
    protected OpportunityApi() {
        restClient = RestClientApi.getInstance();
        restClient.setRequest(Authentication.requestSpecification());
    }

    /**
     * Returns the instance the opportunity API.
     *
     * @return a opportunity API.
     */
    public static OpportunityApi getInstance() {
        return new OpportunityApi();
    }

    /**
     * Add a deserialized json content to the body of 'request' Requestspecification attribute of this class.
     *
     * @param opportunityBody a flat deserialized String containing the key/value data to be provided to a POST or
     *      PATCH request.
     */
    public void setContent(String opportunityBody) {
        restClient.buildSpec(opportunityBody);
    }

    /**
     * Add a json content to the body of 'request' Requestspecification attribute contained in a Map format.
     *
     * @param opportunityBody a Map structure containing the key/value data to be provided to a POST or PATCH request.
     */
    public void setContent(Map opportunityBody) {
        restClient.buildSpec(opportunityBody);
    }

    /**
     * Adds content to the body of 'request' Requestspecification attribute contained within a JSONObject.
     *
     * @param opportunityBody a JSONObject containing the key/value data to be provided to a POST or PATCH request.
     */
    public void setContent(JSONObject opportunityBody) {
        restClient.buildSpec(opportunityBody);
    }

    /**
     * Return the response after requesting an Opportunities.
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
     * Update a previously created Opportunity specified by its id.
     *
     * @param id uniquely identifies a given Opportunity
     * @return a RestAssured Response structure as a result of a successful PATCH request.
     */
    public Response updateOpportunityById(String id) {
        finalEndpoint = OPPORTUNITY_ENDPOINT.concat("/".concat(id));
        response = restClient.patch(finalEndpoint);
        return response;
    }

    /**
     * Delete a previously created Opportunity specified by its id.
     *
     * @param id uniquely identifies a given Opportunity.
     * @return a RestAssured Response structure as a result of a successful DELETE request.
     */
    public Response deleteOpportunityById(String id) {
        finalEndpoint = OPPORTUNITY_ENDPOINT.concat("/".concat(id));
        response = restClient.delete(finalEndpoint);
        return response;
    }

    /**
     * Find a previously created Opportunity specified by its id.
     *
     * @param id uniquely identifies a given Opportunity.
     * @return a RestAssured Response structure as a result of a successful GET request.
     */
    public Response findOpportunityById(String id) {
        finalEndpoint = OPPORTUNITY_ENDPOINT.concat("/".concat(id));
        response = restClient.get(finalEndpoint);
        return response;
    }

    /**
     * Returns a response after requesting a post.
     *
     * @param method to do the request.
     * @return a RestAssured Response structure.
     */
    public Response opportunityResponse(String method) {
        return restClient.apiResponse(method,OPPORTUNITY_ENDPOINT);
    }
}
