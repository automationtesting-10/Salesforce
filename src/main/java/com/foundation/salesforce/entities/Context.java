/*
 * @(#) Context.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.entities;

import io.restassured.response.Response;

/**
 * Context class contains all objects that are shared between steps.
 *
 * @author Cristian Lujan, Jesus Menacho
 * @version 1.0
 */
public class Context {
    private Opportunity opportunity;
    private Task task;
    private Lead lead;
    private Response response;
    private Account account;
    private Case textCase;
    private Contact contact;
    private OpportunityContactRole opportunityContactRole;

    /**
     * Context for creation of the constructor.
     */
    public Context() {
        this.opportunity = new Opportunity();
        this.task = new Task();
        this.lead = new Lead();
        this.account = new Account();
        this.textCase = new Case();
        this.contact = new Contact();
        this.opportunityContactRole = new OpportunityContactRole();
    }

    /**
     * Returns the Opportunity of context.
     *
     * @return opportunity.
     */
    public Opportunity getOpportunity() {
        return opportunity;
    }

    /**
     * Returns the Task of context.
     *
     * @return the Task object associated to this Context object.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Allows to get the Account.
     *
     * @return Account.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Allows to get the case.
     *
     * @return Case.
     */
    public Case getCase() {
        return textCase;
    }

    /**
     * Allows to get the lead.
     *
     * @return lead.
     */
    public Lead getLead() {
        return lead;
    }

    /**
     * Allows to get the response.
     *
     * @return response.
     */
    public Response getResponse() {
        return response;
    }

    /**
     * Allows to set the context response.
     *
     * @param response - Response to be set to the context.
     */
    public void setResponse(Response response) {
        this.response = response;
    }

    /**
     * Method allow to return the object contact.
     *
     * @return contact the contact object.
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Returns the Opportunity contact role of context.
     *
     * @return opportunityContactRole.
     */
    public OpportunityContactRole getOpportunityContactRole() {
        return opportunityContactRole;
    }
}
