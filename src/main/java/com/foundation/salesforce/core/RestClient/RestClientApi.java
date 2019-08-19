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

package com.foundation.salesforce.core.RestClient;

import com.foundation.salesforce.common.AccessToken;
import com.foundation.salesforce.common.Parceo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;

/**
 * RestClientApi class
 *
 * @author Cristian Lujan
 * @version 0.0.1
 */
public class RestClientApi {

    /**
     * Variable for concat the urlApi of API with the endpoint.
     */
    private String urlApi;

    /**
     * Header name for the Url Api.
     */
    private String urlBase;

    /**
     * Header name for the token type
     */
    final String TOKEN_TYPE = "Bearer ";

    /**
     * Header name for de access token
     */
    private String accessToken;

    /**
     * Variable for the response.
     */
    private Response response;

    /**
     * Variable for the request.
     */
    private RequestSpecification request;

    /**
     * Variable for  initialize base api.
     */
    private static RestClientApi instance;

    private ValidatableResponse json;

    /**
     * Constructor of rest client API.
     */
    protected RestClientApi() {
        initialize();
        //requestAuthenticate();
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
     * Initializes the setting for the API.
     */
    private void initialize() {
        //urlBase = ReaderDriverProperties.getInstance().getProperties().get("urlBase");
        //accessToken = ReaderDriverProperties.getInstance().getProperties().get("access_token");
        urlBase = Parceo.getInstance().containtReq().getInstanceUrl();
        accessToken = Parceo.getInstance().containtReq().getAccessToken();
    }

    /**
     * Request authenticate.
     */
    /*
    public void requestAuthenticate() {
        request = new RequestSpecBuilder()
                .setBaseUri(urlBase)
                .setContentType(ContentType.JSON)
                .setAuth(oauth2(accessToken))
                .build();
    }
     */

    public RequestSpecification getRequest() {
        return request;
    }

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
     *
     * @return a response.
     */
    public Response delete(final String endpoint) {
        return apiResponse ("DELETE", endpoint);
    }

    /**
     * Returns a response after requesting a post.
     *
     * @param endpoint to do the request.
     *
     * @return a response.
     */
    public Response post(final String endpoint) {
        return apiResponse ("POST", endpoint);
    }

    /**
     * Returns a response after requesting a put.
     *
     * @param endpoint to do the request.
     *
     * @return a response.
     */
    public Response put(final String endpoint) {
        return apiResponse ("PUT", endpoint);
    }

    /**
     * Returns a response after requesting a patch.
     *
     * @param endpoint to do the request.
     *
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

    public void buildSpec(final String taskBody) {
        request = given().spec(request).contentType(ContentType.JSON).body(taskBody);
    }

    public void buildSpec(final Map taskBody) {
        request = given().spec(request).contentType(ContentType.JSON).body(new JSONObject(taskBody).toString());
    }

    public void buildSpec(final JSONObject taskBody) {
        request = given().spec(request)
                .contentType(ContentType.JSON)
                .body(taskBody.toString());
    }
}
