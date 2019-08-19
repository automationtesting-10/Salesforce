/*
 * Salesforce
 *
 * Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguín, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.steps;

import com.foundation.salesforce.core.TaskApi;
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
 * TaskAcceptanceStepdefs
 *
 * @author Alejandro Sánchez Luizaga
 * @version 0.1.0
 */
public class TaskAcceptanceStepdefs {
    private ValidatableResponse json;
    private Response response;
    private TaskApi taskApi;

    Task task = new Task();

    @Given("a user logs in into the Task page")
    public void a_user_logs_in() {
        taskApi = TaskApi.getInstance();
    }

    @Given("user specifies new body content$")
    public void user_specifies_new_content(Map<String, String> inputContent) {
        taskApi.setContent(inputContent);
    }

    @When("user posts to Task endpoint$")
    public void user_posts_content() {
        this.response = taskApi.postContent();
        task.setId(response.jsonPath().getString("id"));
    }

    @Then("the status code is 201 after creating")
    public void check_create_status_code() {
    }

    @And("creation response includes the following$")
    public void creation_response_includes(Map<String, String> response) {

    }

    @Given("user specifies updated body content$")
    public void user_specifies_updated_content(Map<String, String> inputContent) {
        taskApi.setContent(inputContent);
    }

    @When("user patches Task (.*)")
    public void user_patches_content(String id) {
        taskApi.patchContent(id).prettyPrint();
    }

    @Then("the status code is 204 after updating")
    public void check_update_status_code() {

    }

    @When("user searches for task (.*)")
    public void user_searches_for(String taskId){
        this.response = taskApi.findTaskById(taskId);

    }

    @Then("status code is (\\d+) after finding")
    public void verify_search_status_code(int statusCode){
        json = response.then().statusCode(statusCode);
    }

    @And("search response contains the following$")
    public void search_response_includes(Map<String, String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            json.body(field.getKey(), equalTo(field.getValue()));
        }
    }

    @When("user makes a delete request for task (.*)")
    public void user_makes_delete_request(String taskId){
        this.response = taskApi.findTaskById(taskId);
    }

    @Then("status code is (\\d+) after deleting")
    public void verify_delete_status_code(int statusCode){
        json = response.then().statusCode(statusCode);
    }
}
