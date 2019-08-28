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
    private Contact contact;

    /**
     * Context for creation of the constructor.
     */
    public Context() {
        this.opportunity = new Opportunity();
        this.task = new Task();
        this.lead = new Lead();
        this.account = new Account();
        this.contact = new Contact();
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
     * Method let set the contact object.
     *
     * @param contact the contact object.
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
