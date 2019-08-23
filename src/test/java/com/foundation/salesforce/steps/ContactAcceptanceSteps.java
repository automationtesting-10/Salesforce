/*
 * @(#) ContactAcceptanceSteps.java Copyright (c) 2019 Jala Foundation.
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

import com.foundation.salesforce.core.api.ContactApi;
import com.foundation.salesforce.core.restClient.RestClientApi;
import com.foundation.salesforce.core.utils.EndPoints;
import com.foundation.salesforce.entities.Contact;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

/**
 * Class where it is stored the steps for the feachure are carried out.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class ContactAcceptanceSteps {
    private ContactApi contactApi;
    Contact contact = new Contact();
    private Response response;

    @Given("a user sing in the web page SalesForce")
    public void autheticationUser(){
        contactApi.getInstance();
    }

    @Given("user specifies new contact$")
    public void userCreateNewContact(Map<String, String> inputContent) {
        RestClientApi.getInstance().buildSpec(inputContent);
    }

    @When("User send de request post to contact endpoint")
    public void userSendDeRequestPostToContactEndpoint() {
        response=RestClientApi.getInstance().post(EndPoints.CONTACT_ENDPOINT);
    }

    @Then("User get a {string} status code")
    public void userGetAStatusCode(String statusCode) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
    }

    @When("User makes a delete request for an existing contact")
    public void userMakesADeleteRequestForAnExistingContact() {

    }

    @Then("Status code is {int}")
    public void statusCodeIs(int arg0) {

    }

    @When("User searches for an existing contact")
    public void userSearchesForAnExistingContact() {
    }

    @When("User makes a get request to contact")
    public void userMakesAGetRequestToContact() {
    }
}
