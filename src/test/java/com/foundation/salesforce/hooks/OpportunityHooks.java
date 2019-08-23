package com.foundation.salesforce.hooks;

import com.foundation.salesforce.core.api.OpportunityApi;
import com.foundation.salesforce.entities.Context;
import com.foundation.salesforce.entities.Opportunity;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.util.HashMap;
import java.util.Map;

/**
 * OpportunityHooks class
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class OpportunityHooks {

    private final Context context;
    private Opportunity opportuniy;

    /**
     * Constructor of class.
     *
     * @param context of type context.
     */
    public OpportunityHooks(Context context) {
        this.context = context;
        this.opportuniy = context.getOpportunity();
    }

    /**
     * Deletes an opportunity by id after scenario.
     */
    @After("@deleteNewOpportunity")
    public void afterScenarioDelete() {
        OpportunityApi.getInstance().deleteOpportunityById(opportuniy.getId());
    }

    /**
     * Creates a contact before scenario.
     */
    @Before("@create-contact")
    public void beforeScenario() {
        Map<String,String> createNewContat = new HashMap<>();
        createNewContat.put("LastName", "Contact_Test");
        contact.setId(ContactAPI.getInstance().createContact(createNewContat));
    }
}