/*
 * @(#) TaskSteps.java Copyright (c) 2019 Jala Foundation.
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
import com.foundation.salesforce.entities.Context;

import io.restassured.response.Response;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.json.JSONObject;

import org.testng.Assert;

import java.util.Map;

/**
 * TaskSteps
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class TaskSteps {
    private Context context;
    private TaskApi taskApi;

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
    public TaskSteps(Context context) {
        this.context = context;
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
     *
     * @param inputContent specified as data table in gherkin feature file.
     */
    @Given("user specifies json content")
    public void user_specifies_content(String inputContent) {
        taskApi.setContent(inputContent);
    }

    /**
     *  Sends through a POST request the data needed to create a Task.
     */
    @When("user posts to Task endpoint$")
    public void user_posts_content() {
        Response response = taskApi.postContent();
        context.setResponse(response);
        context.getTask().setId(response.jsonPath().getString("id"));
        response.prettyPrint();
    }

    /**
     * Feeds multiple data values for the creation of multiple Task sObjects in SalesForce.
     *
     * @param status Initial status the Task will be granted.
     * @param priority Initial priority level the Task will be granted.
     */
    @Given("user specifies (.*) and (.*)")
    public void user_specifies_status_priority(String status, String priority) {
        JSONObject jsonContent = new JSONObject();
        jsonContent.put("Status", status);
        jsonContent.put("Priority", priority);
        taskApi.setContent(jsonContent);
    }

    /**
     * Checks the resulting status code.
     */
    @Then("status code is (\\d+)")
    public void verify_status_code(int statusCode){
        Assert.assertEquals(context.getResponse().getStatusCode(), statusCode);
    }

    /**
     * Checks the response obtained after creating a Task.
     *
     * @param response a RestAssured.Response structure.
     */
    @And("response includes the following$")
    public void response_includes(Map<String, String> response) {
        for (Map.Entry<String, String> field : response.entrySet()) {
            Assert.assertEquals(context.getResponse().jsonPath().get(field.getKey()).toString(), field.getValue());
        }
    }

    @And("response complies (.*)")
    public void response_is_valid (String schemaTypeName) {
        boolean actual = ResponseValidation.getInstance().matchesJsonSchema(schemaTypeName, context.getResponse());
        Assert.assertTrue(actual);
    }

    /**
     * Searches for an existing Task.
     */
    @When("user patches an existing task")
    public void user_patches_content() {
        Response response = taskApi.patchContent(context.getTask().getId());
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Searches for a Task that is related to a given id.
     *
     * @param id the id of the Task intended to add content to its body.
     */
    @When("user patches Task ([\\w]{18})")
    public void user_patches_content(String id) {
        Response response = taskApi.patchContent(id);
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Searchs an existing id.
     */
    @When("user searches for an existing task")
    public void user_searches_existing(){
        Response response = taskApi.findTaskById(context.getTask().getId());
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Search a task based on its id.
     */
    @When("user searches for task ([\\w]{18})")
    public void user_searches_for(String taskId){
        Response response = taskApi.findTaskById(taskId);
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Delete an existing Task.
     */
    @When("user makes a delete request for an existing task")
    public void user_makes_delete_request_existing(){
        Response response = taskApi.deleteTaskById(context.getTask().getId());
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Delete a Task based on its id string.
     */
    @When("user makes a delete request for task ([\\w]{18})")
    public void user_makes_delete_request(String taskId){
        Response response = taskApi.deleteTaskById(taskId);
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Retrieve a summary of Task.
     */
    @When("user makes a get request to endpoint")
    public void user_retrieves_summary() {
        Response response = taskApi.retrieveSummaryForTask();
        context.setResponse(response);
        response.prettyPrint();
    }
}
