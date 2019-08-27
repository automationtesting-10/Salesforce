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

public class CaseHooks {
    private Context context;
    private RestClientApi restClientApi;

    /**
     * Initializes the class setting the context.
     *
     * @param context - Context to be set.
     */
    public CaseHooks(Context context) {
        this.context = context;
        restClientApi = RestClientApi.getInstance();
        restClientApi.setRequest(Authentication.requestSpecification());
    }

    /**
     * Creates an case before scenarios.
     */
    @Before("@DeleteCase, @FindCase, @UpdateCase")
    public void createCaseBefore() {
        String name = ValueAppender.prefix() + "case1" + ValueAppender.suffix();
        String key = "type";
        String json = String.format("{\"%s\": \"%s\"}", key, name);
        restClientApi.buildSpec(json);
        Response response = restClientApi.post(EndPoints.CASE_ENDPOINT);
        Map<String, String> creationResponse = response.jsonPath().getMap("$");
        context.getCase().setId(creationResponse.get("id"));
    }

    /**
     * Deletes created Case after scenarios.
     */
    @After(value = "@CaseCreation, @FindCase, @UpdateCase", order = 0)
    public void deleteCaseAfterCreation() {
        restClientApi.delete(EndPoints.CASE_ENDPOINT + "/" + context.getCase().getId());
    }

    /**
     * Sets context's Case id after CaseCreation scenario.
     */
    @After(value = "@CaseCreation", order = 1)
    public void saveCasefterCreation() {
        Map<String, String> creationResponse = context.getResponse().jsonPath().getMap("$");
        context.getCase().setId(creationResponse.get("id"));
    }
}

