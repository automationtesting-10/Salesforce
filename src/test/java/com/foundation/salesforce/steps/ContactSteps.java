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

import com.foundation.salesforce.entities.Context;
import cucumber.api.java.en.And;
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
    private ContactApi contactApi;
    private Context context;

    /**
     * Class constructor.
     * A class constructor instantiates a very shiny and beautiful TaskSteps object.
     * Under normal conditions, a step definition class shouldn't have a constructor method,
     * but for Dependency injection purposes, we are defining a constructor that ultimately is to be
     * scanned and set up by our DI library, i.e. picocontainer.
     *
     * If there's the need for a more specific comment here, please refer to the documentation on
     * dependency injection and specifically about the picocontainer library.
     *
     * @param context An object Task that is going to be instantiated by the DI library.
     */
    public ContactSteps(Context context) {
        this.context = context;
        contactApi = ContactApi.getInstance();
    }

    /**
     * Method to let a build a new contact.
     *
     * @param inputContent specified as data table in gherkin feature file.
     */
    @Given("user specifies new contact$")
    public void userCreateNewContact(Map<String, String> inputContent) {
        contactApi.setContent(inputContent);
    }

    /**
     * Method to let user sent a request.
     */
    @When("User send de request post to contact endpoint")
    public void userSendDeRequestPostToContactEndpoint() {
        Response response = contactApi.postContent();
        context.setResponse(response);
        context.getContact().setId(response.jsonPath().getString("id"));
        response.prettyPrint();
    }

    /**
     * Method to let compare a request.
     *
     * @param statusCode The status code that want to compare.
     */
    @Then("User get a {string} status code")
    public void userGetAStatusCode(String statusCode) {
        Assert.assertEquals(context.getResponse().getStatusCode(), Integer.parseInt(statusCode));
    }

    /**
     * Method to led a specifies a new contact.
     *
     * @param inputContent specified as data table in gherkin feature file.
     */
    @Given("User specifies new contact with firstName")
    public void userSpecifiesNewContactWithFirstName(Map<String, String> inputContent) {
//        RestClientApi.getInstance().buildSpec(inputContent);
        contactApi.setContent(inputContent);
    }

    /**
     * Method to let a sent a request to contact.
     */
    @When("User send de request post to contact endpoint with firstName")
    public void userSendDeRequestPostToContactEndpointWithFirstName() {
        Response response = contactApi.postContent();
        context.setResponse(response);
        context.getContact().setId(response.jsonPath().getString("id"));
        response.prettyPrint();
    }

    /**
     * Method let me get the status code with first name.
     *
     * @param statusCode parameter to compare the status code.
     */
    @Then("User get a {string} status code with firstName")
    public void userGetAStatusCodeWithFirstName(String statusCode) {
        Assert.assertEquals(context.getResponse().getStatusCode(), Integer.parseInt(statusCode));
    }

    /**
     * Method to let me specifies new contact with the first name and email.
     *
     * @param inputContent
     */
    @Given("User specifies new contact with firstName, email")
    public void userSpecifiesNewContactWithFirstNameEmail(Map<String, String> inputContent) {
//        RestClientApi.getInstance().buildSpec(inputContent);
        contactApi.setContent(inputContent);
    }

    /**
     * Method let send a recuest to contact with first name and email.
     */
    @When("User send de request post to contact endpoint with firstName, email")
    public void userSendDeRequestPostToContactEndpointWithFirstNameEmail() {
        Response response = contactApi.postContent();
        context.setResponse(response);
        context.getContact().setId(response.jsonPath().getString("id"));
        response.prettyPrint();
    }

    /**
     * Method to led me get a status code with first name and email.
     *
     * @param statusCode put the status code to do compared.
     */
    @Then("User get a {string} status code with firstName, email")
    public void userGetAStatusCodeWithFirstNameEmail(String statusCode) {
        Assert.assertEquals(context.getResponse().getStatusCode(), Integer.parseInt(statusCode));
    }

    /**
     * Method to let user specifies new contact with first name, email and title.
     *
     * @param inputContent the input is HasMap that containt the value firstName, email, title.
     */
    @Given("User specifies new contact with firstName, email, title")
    public void userSpecifiesNewContactWithFirstNameEmailTitle(Map<String, String> inputContent) {
//        RestClientApi.getInstance().buildSpec(inputContent);
        contactApi.setContent(inputContent);
    }

    /**
     * Method to let a send a request to contact with first name , email and title.
     */
    @When("User send de request post to contact endpoint with firstName, email, title")
    public void userSendDeRequestPostToContactEndpointWithFirstNameEmailTitle() {
        Response response = contactApi.postContent();
        context.setResponse(response);
        context.getContact().setId(response.jsonPath().getString("id"));
        response.prettyPrint();
    }

    /**
     * Method to let get the statutus with the first name, email and title.
     *
     * @param statusCode parameter the status code to compare.
     */
    @Then("User get a {string} status code with firstName, email, title")
    public void userGetAStatusCodeWithFirstNameEmailTitle(String statusCode) {
        Assert.assertEquals(context.getResponse().getStatusCode(), Integer.parseInt(statusCode));
    }

    /**
     * Method to let delete a user existing..
     */
    @When("User deletes an existing contact by Id")
    public void userDeletesAnExistingContactById() {
        Response response = contactApi.deleteTaskById(context.getContact().getId());
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Method to le Delete a contact by id.
     *
     * @param contactID parameter the contact id to compare.
     */
    @When("User deletes a Contact by Id (.*)")
    public void userDeletesAContactByIdQIMRlqEAG(String contactID) {
        Response response = contactApi.deleteTaskById(contactID);
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Method to led find an existing contact by id.
     */
    @When("User finds an existing Contact by Id")
    public void userFindsAnExistingContactById() {
        Response response = contactApi.findTaskById(context.getContact().getId());
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Method to led find a contact by id.
     *
     * @param contactID parameter to contact id to compare.
     */
    @When("User finds a contact by Id ([\\w]{18})")
    public void userFindsAContactByIdQIMKLeEA(String contactID) {
        Response response = contactApi.deleteContactById(contactID);
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Method to le retrieves the summary for a contact.
     */
    @When("User retrieves the summary for Contact")
    public void userRetrievesTheSummaryForContact() {
        Response response = contactApi.retrieveSummary();
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Method to le verify status code.
     *
     * @param statusCode parameter the status code to compare.
     */
    @Then("The status code is (\\d+)")
    public void verify_status_code(int statusCode) {
        Assert.assertEquals(context.getResponse().getStatusCode(), statusCode);
    }

    /**
     * Method to let set build specifications with the last name.
     *
     * @param inputFields parameter the input field to compare.
     */
    @Given("User sets the lastName")
    public void userSetsTheLastName(Map<String, String> inputFields) {
        contactApi.setContent(inputFields);
    }

    /**
     * Method to update the existing the contact by id.
     */
    @When("User updates existing contact by Id")
    public void userUpdatesExistingContactById() {
        Response response = contactApi.patchContent(context.getContact().getId());
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Method to let set the last name to the contact.
     *
     * @param inputFields parameter the input fields.
     */
    @Given("User sets lastName to the contact")
    public void userSetsLastNameToTheContact(Map<String, String> inputFields) {
        contactApi.setContent(inputFields);
    }

    /**
     * Method to let updates contact by id.
     *
     * @param contactID parameter the contact id to let compare.
     */
    @When("User updates contact by Id (.*)")
    public void userUpdatesContactByIdFSA(String contactID) {
        Response response = contactApi.patchContent(contactID);
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Method to let build specifications all right the user provider json.
     *
     * @param jsonBodyString in param type json to compare.
     */
    @Given("User provides the following json")
    public void userProvidesTheFollowingJson(String jsonBodyString) {
        contactApi.setContent(jsonBodyString);
    }

    @And("response contains the following in contact")
    public void responseContainsTheFollowingInContact(Map<String, String> bodyFields) {
        for (Map.Entry<String, String> field : bodyFields.entrySet()) {
            Assert.assertEquals(context.getResponse().jsonPath().get(field.getKey()).toString(), field.getValue());
        }
    }

    @And("In response we can found the following contain")
    public void inResponseWeCanFoundTheFollowingContain(Map<String, String> bodyFields) {
        for (Map.Entry<String, String> field : bodyFields.entrySet()) {
            Assert.assertEquals(context.getResponse().jsonPath().get(field.getKey()).toString(), field.getValue());
        }
    }
}