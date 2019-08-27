/*
 * @(#) CommonSteps.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */
package com.foundation.salesforce.steps;

import com.foundation.salesforce.core.restClient.Authentication;
import com.foundation.salesforce.core.restClient.RestClientApi;
import com.foundation.salesforce.core.utils.ResponseValidation;
import com.foundation.salesforce.entities.Context;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Map;

/**
 * CommonSteps class contains steps that are common for endpoints.
 *
 * @author Melissa Roman
 * @version 1.0
 **/
public class CommonSteps {
    private Context context;
    private RestClientApi requestManager;

    /**
     * Initializes the class setting the context.
     *
     * @param context - Context to be set.
     */
    public CommonSteps(Context context) {
        this.context = context;
        requestManager = RestClientApi.getInstance();
        requestManager.setRequest(Authentication.requestSpecification());
    }

    /**
     * Verifies response's status code.
     *
     * @param statusCode - Expected status code.
     */
    @Then("the status code is {int}")
    public void theStatusCodeIs(int statusCode) {
        Assert.assertEquals(context.getResponse().getStatusCode(), statusCode);
    }

    /**
     * Verifies response's json body.
     *
     * @param bodyFields - Expected fields and values.
     */
    @And("the response includes the following")
    public void responseIncludesTheFollowing(Map<String, String> bodyFields) {
        SoftAssert softAssert = new SoftAssert();
        for (Map.Entry<String, String> field : bodyFields.entrySet()) {
            softAssert.assertEquals(context.getResponse().jsonPath().get(field.getKey()).toString(), field.getValue());
        }
        softAssert.assertAll();
    }

    /**
     * Verifies response's body array of json objects.
     *
     * @param bodyFields - Expected fields and values.
     */
    @And("the response contains the following")
    public void responseContainsTheFollowing(Map<String, String> bodyFields) {
        SoftAssert softAssert = new SoftAssert();
        Map<String, String> responseFirstElement = context.getResponse().jsonPath().getMap("[0]");
        for (Map.Entry<String, String> field : bodyFields.entrySet()) {
            softAssert.assertEquals(responseFirstElement.get(field.getKey()), field.getValue());
        }
        softAssert.assertAll();
    }

    /**
     * Verifies response headers.
     *
     * @param headerFields - Expected headers and values.
     */
    @And("headers include the following")
    public void headersIncludeTheFollowing(Map<String, String> headerFields) {
        SoftAssert softAssert = new SoftAssert();
        for (Map.Entry<String, String> field : headerFields.entrySet()) {
            softAssert.assertEquals(context.getResponse().getHeader(field.getKey()), field.getValue());
        }
        softAssert.assertAll();
    }

    /**
     * Validates json schema of creation response.
     *
     * @param schemaTypeName - Schema to validate.
     */
    @And("the response passes (.*) validation")
    public void responsePassesSchemaValidation (String schemaTypeName) {
        boolean actual = ResponseValidation.getInstance().matchesJsonSchema(schemaTypeName, context.getResponse());
        Assert.assertTrue(actual);
    }
}
