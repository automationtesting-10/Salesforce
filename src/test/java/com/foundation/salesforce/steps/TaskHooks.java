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

package com.foundation.salesforce.steps;

import com.foundation.salesforce.core.api.TaskApi;
import com.foundation.salesforce.entities.Task;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.response.Response;
import org.json.JSONObject;

/**
 * TaskHooks class.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class TaskHooks {
    private Task task;
    private TaskApi taskApi;

    public TaskHooks (Task task) {
        this.task = task;
        taskApi = TaskApi.getInstance();
    }

    @Before("@FindTask, @DeleteTask, @UpdateTask")
    public void before_delete_task() {
        JSONObject jsonContent = new JSONObject();
        jsonContent.put("Status","Not started");
        jsonContent.put("Priority","Low");
        taskApi.setContent(jsonContent);
        Response response = taskApi.postContent();
        response.prettyPrint();
        task.setId(response.jsonPath().getString("id"));
    }
}
