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
import com.foundation.salesforce.entities.Context;
import com.foundation.salesforce.entities.Task;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpStatus;
import org.testng.Assert;

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
    private Task task;

    public TaskAcceptanceSteps(Task task) {
        this.task = task;
        taskApi = TaskApi.getInstance();
    }

    /**
     * Retrieves an authentication token in order to be able to access to Salesforce platform.
     */
    @Given("a user logs in into the Task page")
    public void a_user_logs_in() {
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
        this.response.prettyPrint();
    }

    /**
     * Checks the resulting status code.
     */
    @Then("status code is (\\d+)")
    public void verify_status_code(int statusCode){
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    /**
     * Checks the response obtained after creating a Task.
     *
     * @param response a RestAssured.Response structure.
     */
    @And("response includes the following$")
    public void response_includes(Map<String, String> response) {
        for (Map.Entry<String, String> field : response.entrySet()) {
            Assert.assertEquals(this.response.jsonPath().get(field.getKey()).toString(), field.getValue());
        }
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
        this.response = taskApi.patchContent(id);
        this.response.prettyPrint();
    }

    /**
     * Search a task based on its id.
     */
    @When("user searches for task (.*)")
    public void user_searches_for(String taskId){
        this.response = taskApi.findTaskById(taskId);
        this.response.prettyPrint();
    }

    /**
     * Delete a Task based on its id string.
     */
    @When("user makes a delete request for task (.*)")
    public void user_makes_delete_request(String taskId){
        this.response = taskApi.deleteTaskById(taskId);
        this.response.prettyPrint();
    }
}
