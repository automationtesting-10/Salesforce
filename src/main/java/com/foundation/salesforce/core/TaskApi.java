/*
 * Salesforce API Testing
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

package com.foundation.salesforce.core;

import com.foundation.salesforce.api.Authentication;
import com.foundation.salesforce.core.RestClient.RestClientApi;
import io.restassured.response.Response;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Map;

import static com.foundation.salesforce.core.utils.EndPoints.TASK_ENDPOINT;

/**
 * TaskApi
 *
 * @author Alejandro Sánchez Luizaga
 * @version 0.1.0
 */
public class TaskApi {
    private RestClientApi restClient;
    //private String finalEndpoint;
    //private RequestSpecification request;
    //private Response response;

    /**
     * Class constructor.
     */
    protected TaskApi() {
        restClient = RestClientApi.getInstance();
        restClient.setRequest(Authentication.requestSpecification());
    }

    /**
     * Returns an instance of TaskApi class.
     *
     * @return a account API.
     */
    public static TaskApi getInstance() {
        return new TaskApi();
    }

    public Response findTaskById(String id) {
        return restClient.get(TASK_ENDPOINT.concat("/").concat(id));
    }

    public Response retrieveSummaryForTask() {
        return restClient.get(TASK_ENDPOINT);
    }

    public void setContent(String taskBody) {
        restClient.buildSpec(taskBody);
    }

    public void setContent(Map taskBody) {
        restClient.buildSpec(taskBody);
    }

    public void setContent(JSONObject taskBody) {
        restClient.buildSpec(taskBody);
    }

    public Response patchContent(String id) {
        return restClient.patch(TASK_ENDPOINT.concat("/").concat(id));
    }

    public Response postContent() {
        return restClient.post(TASK_ENDPOINT);
    }

    public Response deleteTaskById(String id) {
        return restClient.delete(TASK_ENDPOINT.concat("/").concat(id));
    }
}
