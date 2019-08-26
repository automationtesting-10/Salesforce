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
 * Class where it is stored the steps for the feature are carried out.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class ContactSteps {
    private RestClientApi requestManager;
    private ContactApi contactApi;
    Contact contact = new Contact();
    private Response response;

    /**
     * Method to let a instance contactApi.
     */
    @Given("a user sing in the web page SalesForce")
    public void autheticationUser() {
        contactApi.getInstance();
    }

    /**
     * Method to let a build a new contact.
     *
     * @param inputContent specified as data table in gherkin feature file.
     */
    @Given("user specifies new contact$")
    public void userCreateNewContact(Map<String, String> inputContent) {
        RestClientApi.getInstance().buildSpec(inputContent);
    }

    /**
     * Method to let user sent a request.
     */
    @When("User send de request post to contact endpoint")
    public void userSendDeRequestPostToContactEndpoint() {
        response = RestClientApi.getInstance().post(EndPoints.CONTACT_ENDPOINT);
    }

    /**
     * Method to let compare a recuest.
     *
     * @param statusCode to let me put the status code that want to compare.
     */
    @Then("User get a {string} status code")
    public void userGetAStatusCode(String statusCode) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
    }

    /**
     * Method to led a specifies a new contact.
     *
     * @param inputContent specified as data table in gherkin feature file.
     */
    @Given("User specifies new contact with firstName")
    public void userSpecifiesNewContactWithFirstName(Map<String, String> inputContent) {
        RestClientApi.getInstance().buildSpec(inputContent);
    }

    /**
     * Method to let a sent a request to contact.
     */
    @When("User send de request post to contact endpoint with firstName")
    public void userSendDeRequestPostToContactEndpointWithFirstName() {
        response = RestClientApi.getInstance().post(EndPoints.CONTACT_ENDPOINT);
    }

    /**
     * Method let me get the status code with first name.
     *
     * @param statusCode
     */
    @Then("User get a {string} status code with firstName")
    public void userGetAStatusCodeWithFirstName(String statusCode) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
    }

    /**
     * Method to let me specifies new contact with the first name and email.
     *
     * @param inputContent
     */
    @Given("User specifies new contact with firstName, email")
    public void userSpecifiesNewContactWithFirstNameEmail(Map<String, String> inputContent) {
        RestClientApi.getInstance().buildSpec(inputContent);
    }

    /**
     * Method let send a recuest to contact with first name and email.
     */
    @When("User send de request post to contact endpoint with firstName, email")
    public void userSendDeRequestPostToContactEndpointWithFirstNameEmail() {
        response = RestClientApi.getInstance().post(EndPoints.CONTACT_ENDPOINT);
    }

    /**
     * Method to led me get a status code with first name and email.
     *
     * @param statusCode put the status code to do compared.
     */
    @Then("User get a {string} status code with firstName, email")
    public void userGetAStatusCodeWithFirstNameEmail(String statusCode) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
    }

    /**
     * Method to let user specifies new contact with first name, email and title.
     *
     * @param inputContent the input is HasMap that containt the value firstName, email, title.
     */
    @Given("User specifies new contact with firstName, email, title")
    public void userSpecifiesNewContactWithFirstNameEmailTitle(Map<String, String> inputContent) {
        RestClientApi.getInstance().buildSpec(inputContent);
    }

    /**
     * Method to let a send a request to contact with first name , email and title.
     */
    @When("User send de request post to contact endpoint with firstName, email, title")
    public void userSendDeRequestPostToContactEndpointWithFirstNameEmailTitle() {
        response = RestClientApi.getInstance().post(EndPoints.CONTACT_ENDPOINT);
    }

    /**
     * Method to let get the statutus with the first name, email and title.
     *
     * @param statusCode
     */
    @Then("User get a {string} status code with firstName, email, title")
    public void userGetAStatusCodeWithFirstNameEmailTitle(String statusCode) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
    }

    /**
     * Method to let delete a user existing..
     */
    @When("User deletes an existing contact by Id")
    public void userDeletesAnExistingContactById() {
        response = requestManager.delete(EndPoints.CONTACT_ENDPOINT + "/" + "00Q3i000002MRlqEA1");
        response.prettyPrint();
    }

    /**
     * Method to le Delete a contact by id.
     *
     * @param contactID
     */
    @When("User deletes a Contact by Id (.*)")
    public void userDeletesAContactByIdQIMRlqEAG(int contactID) {
        response = requestManager.delete(EndPoints.CONTACT_ENDPOINT + "/" + contactID);
        response.prettyPrint();
    }

    /**
     * Method to led find an existing contact by id.
     */
    @When("User finds an existing Contact by Id")
    public void userFindsAnExistingContactById() {
        response = requestManager.get(EndPoints.CONTACT_ENDPOINT + "/" + "");
        response.prettyPrint();
    }

    /**
     * Method to led find a contact by id.
     *
     * @param contactID
     */
    @When("User finds a contact by Id (.*)")
    public void userFindsAContactByIdQIMKLeEA(int contactID) {
        response = requestManager.get(EndPoints.CONTACT_ENDPOINT + "/" + contactID);
        response.prettyPrint();
    }

    /**
     * Method to le retrieves the summary for a contact.
     */
    @When("User retrieves the summary for Contact")
    public void userRetrievesTheSummaryForContact() {
        response = requestManager.get(EndPoints.CONTACT_ENDPOINT);
        response.prettyPrint();
    }

    /**
     * Method to le verify status code.
     *
     * @param statusCode
     */
    @Then("The status code is (\\d+)")
    public void verify_status_code(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    /**
     * Method to let set build specifications with the last name.
     *
     * @param inputFields
     */
    @Given("User sets the lastName")
    public void userSetsTheLastName(Map<String, String> inputFields) {
        requestManager.buildSpec(inputFields);
    }

    /**
     * Method to update the existing the contact by id.
     */
    @When("User updates existing contact by Id")
    public void userUpdatesExistingContactById() {
        response = requestManager.patch(EndPoints.CONTACT_ENDPOINT + "/" + "");
        response.prettyPrint();
    }

    /**
     * Method to let set the last name to the contact.
     *
     * @param inputFields
     */
    @Given("User sets lastName to the contact")
    public void userSetsLastNameToTheContact(Map<String, String> inputFields) {
        requestManager.buildSpec(inputFields);
    }

    /**
     * Method to let updates contact by id.
     *
     * @param contactID
     */
    @When("User updates contact by Id (.*)")
    public void userUpdatesContactByIdFSA(int contactID) {
        response = requestManager.get(EndPoints.CONTACT_ENDPOINT + "/" + contactID);
        response.prettyPrint();

    }

    /**
     * Method to let build specifications all right the user provider json.
     *
     * @param jsonBodyString in this
     */
    @Given("User provides the following json")
    public void userProvidesTheFollowingJson(String jsonBodyString) {
        requestManager.buildSpec(jsonBodyString);
    }

}
