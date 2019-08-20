package com.foundation.salesforce.Hooks;

import com.foundation.salesforce.core.api.OpportunityApi;
import com.foundation.salesforce.entities.Context;
import cucumber.api.java.After;

/**
 * Hooks class
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class Hooks {

    private final Context context;
    private OpportunityApi opportunityApi;

    public Hooks(Context context) {
        this.context = context;
    }

    @After("@deleteNewWorkspace")
    public void deleteNewWorkspace() {
        OpportunityApi.getInstance().deleteOpportunityById(context.getOpportunity().getId());
    }
}