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
import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
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
     *
     * @param inputContent specified as data table in gherkin feature file.
     */
    @Given("user specifies body content$")
    public void user_specifies_content(Map<String, String> inputContent) {
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

    @And("(.*) schema is valid")
    public void response_is_valid (String schemaTypeName) {
        StringBuilder stringAccumulator = new StringBuilder();
        char currentCharacter = ' ';
        for (int characterIterator = 0; characterIterator < schemaTypeName.length(); characterIterator++) {
            if (currentCharacter == ' ' && schemaTypeName.charAt(characterIterator) != ' ') {
                stringAccumulator.append(Character.toUpperCase(schemaTypeName.charAt(characterIterator)));
            }
            else {
                stringAccumulator.append(schemaTypeName.charAt(characterIterator));
            }
            currentCharacter = schemaTypeName.charAt(characterIterator);
        }
        schemaTypeName = stringAccumulator.toString().replaceAll("\\s","").trim();

        InputStream inputStream = null;
        try  {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(schemaTypeName.concat(".json"));
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(new JSONObject(this.response.jsonPath().getMap("$")));
        }
        catch (ValidationException npvex) {
            Assert.fail("Мне похуй!");
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

    @When("user makes a get request to endpoint")
    public void user_retrieves_summary() {
        this.response = taskApi.retrieveSummaryForTask();
        this.response.prettyPrint();
    }
}
