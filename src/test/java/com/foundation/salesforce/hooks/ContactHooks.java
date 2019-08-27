package com.foundation.salesforce.hooks;

import com.foundation.salesforce.core.api.ContactApi;
import com.foundation.salesforce.core.utils.ValueAppender;
import com.foundation.salesforce.entities.Context;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.response.Response;

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
    public void before_delete_task() {
        String name = ValueAppender.prefix() + "Account" + ValueAppender.suffix();
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
    public void after_create_contact() {
        contactApi.deleteTaskById(context.getContact().getId());
    }
}
