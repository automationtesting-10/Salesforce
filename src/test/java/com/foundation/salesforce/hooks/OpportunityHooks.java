package com.foundation.salesforce.hooks;

import com.foundation.salesforce.core.api.OpportunityApi;
import com.foundation.salesforce.entities.Context;
import com.foundation.salesforce.entities.Opportunity;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * OpportunityHooks class.
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class OpportunityHooks {
    private final Context context;
    private Opportunity opportunity;
    private OpportunityApi opportunityApi;
    private Response response;

    /**
     * Constructor of class.
     *
     * @param context of type context.
     */
    public OpportunityHooks(Context context) {
        this.context = context;
        this.opportunity = context.getOpportunity();
        opportunityApi = OpportunityApi.getInstance();
    }

    /**
     * Deletes an opportunity by id after scenario.
     */
    @After("@DeleteOpportunity")
    public void afterScenarioDelete() {
        opportunityApi.deleteOpportunityById(opportunity.getId());
    }

    /**
     * Creates a opportunity before scenario.
     */
    @Before("@CreateOpportunity")
    public void beforeScenarioCreate() {
        Map<String, String> createNewOpportunity = new HashMap<>();
        createNewOpportunity.put("Name", "TestApi");
        createNewOpportunity.put("CloseDate", "2019-01-01");
        createNewOpportunity.put("StageName", "Prospecting");
        opportunityApi.setContent(createNewOpportunity);
        response = opportunityApi.createOpportunity();
        context.setResponse(response);
        opportunity.setId(response.jsonPath().getString("id"));
    }
}
