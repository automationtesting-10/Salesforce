/*
 * @(#) LeadHooks.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.hooks;

import com.foundation.salesforce.core.restClient.Authentication;
import com.foundation.salesforce.core.restClient.RestClientApi;
import com.foundation.salesforce.core.utils.EndPoints;
import com.foundation.salesforce.core.utils.ValueAppender;
import com.foundation.salesforce.entities.Context;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.response.Response;

import java.util.Map;

/**
 * LeadHooks class contains before and after actions for lead endpoint.
 *
 * @author Melissa Román
 * @version 1.0
 */
public class LeadHooks {
    private Context context;
    private RestClientApi requestManager;

    /**
     * Initializes the class setting the context.
     *
     * @param context - Context to be set.
     */
    public LeadHooks(Context context) {
        this.context = context;
        requestManager = RestClientApi.getInstance();
        requestManager.setRequest(Authentication.requestSpecification());
    }

    /**
     * Creates a lead before tagged scenarios.
     */
    @Before("@DeleteLead, @FindLead, @UpdateLead")
    public void createLeadBefore() {
        String company = ValueAppender.prefix() + "Company" + ValueAppender.suffix();
        requestManager.buildSpec("{\n" +
                "\t\"Company\": \"" + company + "\",\n" +
                "\t\"LastName\": \"Fisk\"\n" +
                "}");
        Response response = requestManager.post(EndPoints.LEAD_ENDPOINT);
        Map<String, String> creationResponse = response.jsonPath().getMap("$");
        context.getLead().setId(creationResponse.get("id"));
    }

    /**
     * Deletes created lead after tagged scenarios.
     */
    @After(value = "@LeadCreation, @FindLead, @UpdateLead, @SeveralLeadsCreation", order = 0)
    public void deleteLeadAfterCreation() {
        requestManager.delete(EndPoints.LEAD_ENDPOINT + "/" + context.getLead().getId());
    }

    /**
     * Sets context's lead id after LeadCreation scenario.
     */
    @After(value = "@LeadCreation, @SeveralLeadsCreation", order = 1)
    public void saveLeadAfterCreation() {
        Map<String, String> creationResponse = context.getResponse().jsonPath().getMap("$");
        context.getLead().setId(creationResponse.get("id"));
    }
}
