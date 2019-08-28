/*
 * @(#) OpportunityContactRoleStep.java Copyright (c) 2019 Jala Foundation.
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

import com.foundation.salesforce.core.api.OpportunityApi;
import com.foundation.salesforce.entities.Context;
import com.foundation.salesforce.entities.OpportunityContactRole;
import com.github.javafaker.Faker;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.json.JSONObject;

/**
 * OpportunityContactRoleStep class.
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class OpportunityContactRoleStep {
    private OpportunityApi opportunityApi;
    private Context context;
    private Response response;
    private OpportunityContactRole opportunityContactRole;
    private Faker faker = new Faker();

    /**
     * Constructor of opportunity com.foundation.salesforce.steps sending the context.
     *
     * @param context init the context.
     */
    public OpportunityContactRoleStep(final Context context) {
        this.context = context;
        opportunityContactRole = context.getOpportunityContactRole();
        opportunityApi = OpportunityApi.getInstance();
    }

    /**
     * Create an existing Opportunity id and Contact id.
     */
    @Given("User set up the data with contact id")
    public void setUpTheDataWithContactId() {
        JSONObject inputBody = new JSONObject();
        String contactId = context.getContact().getId();
        String opportunityId = context.getOpportunity().getId();
        inputBody.put("OpportunityId", opportunityId);
        inputBody.put("ContactId", contactId);
        opportunityApi.setContent(inputBody);
    }

    /**
     * Sends through a method request the data needed a opportunityContactRole.
     *
     * @param method for end point.
     */
    @When("User send request (.*) to opportunity contact role endpoint")
    public void sendRequestPOSTToOpportunityContactRoleEndpoint(String method) {
        response = opportunityApi.opportunityContactRoleResponse(method);
        opportunityContactRole.setId(response.jsonPath().getString("id"));
        context.setResponse(response);
        this.response.prettyPrint();
    }

    /**
     * Delete an existing Opportunity.
     *
     * @param method for end point.
     */
    @When("User send request (.*) to new opportunity contact role endpoint")
    public void sendRequestDELETEToNewOpportunityContactRoleEndpoint(String method) {
        response = opportunityApi.opportunityContactRoleResponse(method, opportunityContactRole.getId());
        context.setResponse(response);
        this.response.prettyPrint();
    }
}
