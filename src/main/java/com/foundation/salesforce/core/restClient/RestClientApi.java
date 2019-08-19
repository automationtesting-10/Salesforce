/*
 * @(#) RestClientApi.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.core.restClient;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * RestClientApi class
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class RestClientApi {
    private RequestSpecification request;
    private static RestClientApi instance;

    /**
     * Constructor of rest client API.
     */
    protected RestClientApi() {
    }

    /**
     * Returns an instance of RestClientAPI.
     *
     * @return a instance.
     */
    public static RestClientApi getInstance() {
        if (instance == null) {
            instance = new RestClientApi();
        }
        return instance;
    }

    /**
     * Returns this class' RequestSpecification request.
     *
     * @return
     */
    public RequestSpecification getRequest() {
        return request;
    }

    /**
     * Sets this class' RequestSpecification request.
     *
     * @param request
     */
    public void setRequest(RequestSpecification request) {
        this.request = request;
    }

    /**
     * Returns a response after requesting a get.
     *
     * @param endpoint to do the request.
     * @return a response.
     */
    public Response get(final String endpoint) {
        return apiResponse ("GET", endpoint);
    }

    /**
     * Returns a response after requesting a delete.
     *
     * @param endpoint to do the request.
     * @return a response.
     */
    public Response delete(final String endpoint) {
        return apiResponse ("DELETE", endpoint);
    }

    /**
     * Returns a response after requesting a post.
     *
     * @param endpoint to do the request.
     * @return a response.
     */
    public Response post(final String endpoint) {
        return apiResponse ("POST", endpoint);
    }

    /**
     * Returns a response after requesting a put.
     *
     * @param endpoint to do the request.
     * @return a response.
     */
    public Response put(final String endpoint) {
        return apiResponse ("PUT", endpoint);
    }

    /**
     * Returns a response after requesting a patch.
     *
     * @param endpoint to do the request.
     * @return a response.
     */
    public Response patch(final String endpoint) {
        return apiResponse ("PATCH", endpoint);
    }

    /**
     * Returns a response after requesting a post.
     *
     * @param httpMethod to do the request.
     * @return a response
     */
    public Response apiResponse(String httpMethod, final String endPoint) {
        return given().spec(request).when().request(httpMethod, endPoint);
    }

    /**
     * Add a deserialized json content to the body of this class request attribute.
     *
     * @param taskBody A string containing plain deserialized key/value pairs.
     */
    public void buildSpec(final String taskBody) {
        request = given().spec(request).contentType(ContentType.JSON).body(taskBody);
    }

    /**
     * Add a json content contained in a Map format to the body of this class request attribute.
     *
     * @param taskBody A map structure containing the key/value pairs to be passed.
     */
    public void buildSpec(final Map taskBody) {
        request = given().spec(request).contentType(ContentType.JSON).body(new JSONObject(taskBody).toString());
    }

    /**
     * Adds content to the body of this class request attribute contained within a JSONObject.
     *
     * @param taskBody
     */
    public void buildSpec(final JSONObject taskBody) {
        request = given().spec(request)
                .contentType(ContentType.JSON)
                .body(taskBody.toString());
    }
}
