/*
 * @(#) ContactHooks.java Copyright (c) 2019 Jala Foundation.
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

import com.foundation.salesforce.core.api.ContactApi;
import com.foundation.salesforce.core.utils.ValueAppender;
import com.foundation.salesforce.entities.Context;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import io.restassured.response.Response;

/**
 * LeadHooks class contains before and after actions for lead endpoint.
 *
 * @author Jesus Menacho
 * @version 1.0
 **/
public class ContactHooks {
    private Context context;
    private ContactApi contactApi;

    /**
     * Initializes the class setting the context.
     *
     * @param context - Context to be set.
     */
    public ContactHooks(Context context) {
        this.context = context;
        contactApi = ContactApi.getInstance();
    }

    /**
     * Creates a Task before the scenarios.
     */
    @Before("@FindContact, @DeleteContact, @UpdateContact")
    public void beforeDeleteContact() {
        String name = ValueAppender.prefix() + "Contact" + ValueAppender.suffix();
        String key = "lastName";
        String json = String.format("{\"%s\": \"%s\"}", key, name);
        contactApi.setContent(json);
        Response response = contactApi.postContent();
        context.setResponse(response);
        context.getContact().setId(response.jsonPath().getString("id"));
        response.prettyPrint();
    }

    /**
     * Deletes any created count(s) in order to keep the environment clean.
     */
    @After(value = "@CreateContact, @UpdateContact, @FindContact" + "~@Negative")
    public void afterCreateContact() {
        contactApi.deleteContactById(context.getContact().getId());
    }
}
