/*
 * @(#) TaskHooks.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.hooks;

import com.foundation.salesforce.core.api.TaskApi;
import com.foundation.salesforce.entities.Context;
import com.foundation.salesforce.entities.Task;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.response.Response;
import org.json.JSONObject;

/**
 * TaskHooks class.
 * Implementations of various actions to be run according to @ tags.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class TaskHooks {
    private Context context;
    private TaskApi taskApi;

    /**
     * Class constructor.
     *
     * @param context Object to be set by dependecy injection.
     */
    public TaskHooks (Context context) {
        this.context = context;
        taskApi = TaskApi.getInstance();
    }

    /**
     * Creates a Task before the scenarios tagged as: @FindTask, @DeleteTask, @UpdateTask.
     */
    @Before("@FindTask, @DeleteTask, @UpdateTask")
    public void before_delete_task() {
        JSONObject jsonContent = new JSONObject();
        jsonContent.put("Status","Not started");
        jsonContent.put("Priority","Low");
        taskApi.setContent(jsonContent);
        Response response = taskApi.postContent();
        response.prettyPrint();
        context.getTask().setId(response.jsonPath().getString("id"));
    }

    /**
     * Saves Task id after creation.
     */
    @After(value = "@CreateTask", order = 1)
    public void save_created_task() {
        context.getTask().setId(context.getResponse().jsonPath().getString("id"));
    }

    /**
     * Deletes any Task(s) created by the tagged scenarios specified.
     */
    @After(value = "@CreateTask, @FindTask, @UpdateTask, @CreateTasks", order = 0)
    public void after_create_task() {
        taskApi.deleteTaskById(context.getTask().getId());
    }
}
