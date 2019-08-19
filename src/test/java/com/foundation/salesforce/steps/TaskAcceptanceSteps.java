/*
 * @(#) TaskAcceptanceSteps.java Copyright (c) 2019 Jala Foundation.
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

import com.foundation.salesforce.core.api.TaskApi;
import com.foundation.salesforce.entities.Task;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * TaskAcceptanceSteps
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class TaskAcceptanceSteps {
    private ValidatableResponse json;
    private Response response;
    private TaskApi taskApi;

    Task task = new Task();

    /**
     * Retrieves an authentication token in order to be able to access to Salesforce platform.
     */
    @Given("a user logs in into the Task page")
    public void a_user_logs_in() {
        taskApi = TaskApi.getInstance();
    }

    /**
     *
     * @param inputContent specified as data table in gherkin feature file.
     */
    @Given("user specifies new body content$")
    public void user_specifies_new_content(Map<String, String> inputContent) {
        taskApi.setContent(inputContent);
    }

    /**
     *  Sends throuhg a POST request the data needed to create a Task.
     */
    @When("user posts to Task endpoint$")
    public void user_posts_content() {
        this.response = taskApi.postContent();
        task.setId(response.jsonPath().getString("id"));
    }

    /**
     * Checks the resulting status code.
     */
    @Then("the status code is 201 after creating")
    public void check_create_status_code() {
    }

    /**
     * Checks the response obtained after creating a Task.
     *
     * @param response
     */
    @And("creation response includes the following$")
    public void creation_response_includes(Map<String, String> response) {
        //TODO Implement TestNG Assertion
    }

    /**
     * Appends a body part to this class' RequestSpecification attributes.
     * @param inputContent A Map structure containing the key/value pairs that are intended to be passed to the
     *     API endpoint.
     */
    @Given("user specifies updated body content$")
    public void user_specifies_updated_content(Map<String, String> inputContent) {
        taskApi.setContent(inputContent);
    }

    /**
     * Searches for a Task that is related to a given id.
     * @param id the id of the Task intended to add content to its body.
     */
    @When("user patches Task (.*)")
    public void user_patches_content(String id) {
        taskApi.patchContent(id).prettyPrint();
    }

    /**
     * Checks the resulting status code.
     */
    @Then("the status code is 204 after updating")
    public void check_update_status_code() {
        //TODO Implement TestNG Assertion
    }

    /**
     * Search a task based on its id.
     */
    @When("user searches for task (.*)")
    public void user_searches_for(String taskId){
        this.response = taskApi.findTaskById(taskId);
    }

    /**
     * Checks the resulting status code.
     */
    @Then("status code is (\\d+) after finding")
    public void verify_search_status_code(int statusCode){
        json = response.then().statusCode(statusCode);
    }

    /**
     * Check also if the response may contain something relevant.
     */
    @And("search response contains the following$")
    public void search_response_includes(Map<String, String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            json.body(field.getKey(), equalTo(field.getValue()));
        }
    }

    /**
     * Delete a Task based on its id string.
     */
    @When("user makes a delete request for task (.*)")
    public void user_makes_delete_request(String taskId){
        this.response = taskApi.findTaskById(taskId);
    }

    /**
     * Checks the resulting status code.
     */
    @Then("status code is (\\d+) after deleting")
    public void verify_delete_status_code(int statusCode){
        json = response.then().statusCode(statusCode);
    }
}
