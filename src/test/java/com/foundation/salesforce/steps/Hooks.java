/*
 * @(#) Hooks.java Copyright (c) 2019 Jala Foundation.
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
import com.foundation.salesforce.core.utils.EndPoints;
import com.foundation.salesforce.entities.Context;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Hooks class contains before and after actions for Lead endpoint.
 *
 * @author Melissa Román
 * @version 1.0
 **/
public class Hooks {
    private Context context;
    private RestClientApi requestManager;

    /**
     * Initializes the class setting the context.
     *
     * @param context - Context to be set.
     */
    public Hooks(Context context) {
        this.context = context;
        requestManager = RestClientApi.getInstance();
        requestManager.setRequest(Authentication.requestSpecification());
    }

    /**
     * Deletes created lead after LeadCreation scenario.
     */
    @After("@LeadCreation, @FindLead, @UpdateLead")
    public void deleteLeadAfterCreation() {
        requestManager.delete(EndPoints.LEAD_ENDPOINT + "/" + context.getLead().getId());
    }

    @Before("@DeleteLead, @FindLead, @UpdateLead")
    public void createLeadBefore() {
        requestManager.buildSpec("{\n" +
                "\t\"Company\": \"Enterprise\",\n" +
                "\t\"LastName\": \"Fisk\"\n" +
                "}");
        Response response = requestManager.post(EndPoints.LEAD_ENDPOINT);
        Map<String, String> creationResponse = response.jsonPath().getMap("$");
        context.getLead().setId(creationResponse.get("id"));
    }
}
