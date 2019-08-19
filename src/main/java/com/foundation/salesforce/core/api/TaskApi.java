/*
 * @(#) TaskApi.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.core.api;

import com.foundation.salesforce.core.restClient.Authentication;
import com.foundation.salesforce.core.restClient.RestClientApi;
import io.restassured.response.Response;
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

    /**
     * Add a deserialized json content to the body of 'request' Requestpecification attribute of this class
     *
     * @param taskBody
     */
    public void setContent(String taskBody) {
        restClient.buildSpec(taskBody);
    }

    /**
     * Add a json content to the body of 'request' Requestpecification attribute contained in a Map format.
     *
     * @param taskBody
     */
    public void setContent(Map taskBody) {
        restClient.buildSpec(taskBody);
    }

    /**
     * Adds content to the body of 'request' Requestpecification attribute contained within a JSONObject.
     *
     * @param taskBody
     */
    public void setContent(JSONObject taskBody) {
        restClient.buildSpec(taskBody);
    }

    /**
     * Returns a RestAssured Response as a result of a successful PATCH request.
     *
     * @param id
     * @return
     */
    public Response patchContent(String id) {
        return restClient.patch(TASK_ENDPOINT.concat("/").concat(id));
    }

    /**
     * Returns a RestAssured Response as a result of a successful POST request.
     *
     * @return
     */
    public Response postContent() {
        return restClient.post(TASK_ENDPOINT);
    }

    /**
     * Returns a previousy created Task specified by its id.
     *
     * @param id
     * @return
     */
    public Response findTaskById(String id) {
        return restClient.get(TASK_ENDPOINT.concat("/").concat(id));
    }

    /**
     * Returns an overview of Task's metadata as well as a list of the most recently used Task records.
     *
     * @return
     */
    public Response retrieveSummaryForTask() {
        return restClient.get(TASK_ENDPOINT);
    }

    /**
     * Delete a previously created Task specified by its id.
     *
     * @param id
     * @return
     */
    public Response deleteTaskById(String id) {
        return restClient.delete(TASK_ENDPOINT.concat("/").concat(id));
    }
}
