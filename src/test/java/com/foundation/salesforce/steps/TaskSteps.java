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
import com.foundation.salesforce.entities.Context;

import com.github.javafaker.Faker;
import io.restassured.response.Response;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * TaskSteps class.
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
    public void userSpecifiesContent(Map<String, String> inputContent) {
        taskApi.setContent(inputContent);
    }

    /**
     *
     * @param inputContent specified as data table in gherkin feature file.
     */
    @Given("user specifies json content")
    public void userSpecifiesContent(String inputContent) {
        taskApi.setContent(inputContent);
    }

    /**
     *  Sends through a POST request the data needed to create a Task.
     */
    @When("user posts to Task endpoint$")
    public void userPostsContent() {
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
    public void userSpecifiesStatusPriority(String status, String priority) {
        JSONObject jsonContent = new JSONObject();
        jsonContent.put("Status", status);
        jsonContent.put("Priority", priority);
        taskApi.setContent(jsonContent);
    }

    /**
     * Creates a requirement Task to be searched for in the scenario.
     *
     * @param inputContent
     */
    @Given("a Task have been created with")
    public void aTaskCreatedWith(Map<String, String> inputContent) {
        taskApi.setContent(inputContent);
        Response response = taskApi.postContent();
        context.setResponse(response);
        context.getTask().setId(response.jsonPath().getString("id"));
        response.prettyPrint();
    }

    /**
     * Updates a requirement Task to be searched for in the scenario.
     *
     * @param inputContent
     */
    @Given("an existing Task have been updated with")
    public void aTaskUpdatedWith(Map<String, String> inputContent) {
        taskApi.setContent(inputContent);
        Response response = taskApi.patchContent(context.getTask().getId());
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Searches for an existing Task.
     */
    @When("user patches an existing task")
    public void userPatchesContent() {
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
    public void userPatchesContent(String id) {
        Response response = taskApi.patchContent(id);
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Searchs an existing id.
     */
    @When("user searches for an existing task")
    public void userSearchesExisting() {
        Response response = taskApi.findTaskById(context.getTask().getId());
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Search a task based on its id.
     *
     * @param taskId The id of the Task searched for.
     */
    @When("user searches for task ([\\w]{18})")
    public void userSearchesFor(String taskId) {
        Response response = taskApi.findTaskById(taskId);
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Delete an existing Task.
     */
    @When("user makes a delete request for an existing task")
    public void userMakesDeleteRequestExisting() {
        Response response = taskApi.deleteTaskById(context.getTask().getId());
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Delete a Task based on its id string.
     *
     * @param taskId the Id of the Task that is intended to be deleted.
     */
    @When("user makes a delete request for task ([\\w]{18})")
    public void userMakesDeleteRequest(String taskId) {
        Response response = taskApi.deleteTaskById(taskId);
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Retrieve a summary of Task.
     */
    @When("user makes a get request to endpoint")
    public void userRetrievesSummary() {
        Response response = taskApi.retrieveSummaryForTask();
        context.setResponse(response);
        response.prettyPrint();
    }

    /**
     * Sets a random invalid value for a given parametrized field.
     *
     * @param length input string length.
     * @param inputField input field.
     */
    @Given("user provides value list with (\\d+) characters")
    public void userProvidesValueListAndLength(int length, List<String> inputField) {
        Faker faker = new Faker();
        String value = faker.number().digits(length);
        JSONObject inputBody = new JSONObject().put(inputField.get(0), value);
        taskApi.setContent(inputBody);
    }

    /**
     * Sets a random invalid value for a given parametrized field.
     *
     * @param length input string length.
     * @param inputField input field.
     */
    @Given("user provides value table with (\\d+) characters")
    public void userProvidesValueMapAndLength(int length, Map<String, String> inputField) {
        Faker faker = new Faker();
        String value = faker.number().digits(length);
        JSONObject inputBody = new JSONObject(inputField);
        inputBody = new JSONObject().put(inputField.keySet().toArray()[2].toString(), value);
        taskApi.setContent(inputBody);
    }
}
