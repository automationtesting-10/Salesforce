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
import com.foundation.salesforce.core.utils.ResponseValidation;
import com.foundation.salesforce.entities.Task;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.testng.Assert;

import java.util.Map;

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

    /**
     * Class constructor.
     * A class constructor instantiates a very shiny and beautiful TaskAcceptanceSteps object.
     * Under normal conditions, a step definition class shouldn't have a constructor method,
     * but for Dependency injection purposes, we are defining a constructor that ultimately is to be
     * scanned and set up by our DI library, i.e. picocontainer.
     *
     * If there's the need for a more specific comment here, please refer to the documentation on
     * dependency injection and specifically about the picocontainer library.
     *
     * @param task An object Task taht is going to be instantiated by the DI library.
     */
    public TaskAcceptanceSteps(Task task) {
        this.task = task;
        taskApi = TaskApi.getInstance();
    }

    /**
     *
     * @param inputContent specified as data table in gherkin feature file.
     */
    @Given("user specifies body content$")
    public void user_specifies_content(Map<String, String> inputContent) {
        taskApi.setContent(inputContent);
    }

    /**
     *  Sends through a POST request the data needed to create a Task.
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

    @And("response complies (.*)")
    public void response_is_valid (String schemaTypeName) {
        boolean actual = ResponseValidation.getInstance().matchesJsonSchema(schemaTypeName, this.response);
        Assert.assertTrue(actual);
    }

    /**
     * Searches for an existing Task.
     */
    @When("user patches an existing task")
    public void user_patches_content() {
        this.response = taskApi.patchContent(task.getId());
        this.response.prettyPrint();
    }

    /**
     * Searches for a Task that is related to a given id.
     *
     * @param id the id of the Task intended to add content to its body.
     */
    @When("user patches Task (.*)")
    public void user_patches_content(String id) {
        this.response = taskApi.patchContent(id);
        this.response.prettyPrint();
    }

    /**
     * Searchs an existing id.
     */
    @When("user searches for an existing task")
    public void user_searches_existing(){
        this.response = taskApi.findTaskById(task.getId());
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
     * Delete an existing Task.
     */
    @When("user makes a delete request for an existing task")
    public void user_makes_delete_request_existing(){
        this.response = taskApi.deleteTaskById(task.getId());
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

    /**
     * Retrieve a summary of Task.
     */
    @When("user makes a get request to endpoint")
    public void user_retrieves_summary() {
        this.response = taskApi.retrieveSummaryForTask();
        this.response.prettyPrint();
    }
}
