/*
 * @(#) ContactApi.java Copyright (c) 2019 Jala Foundation.
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
import static com.foundation.salesforce.core.utils.EndPoints.CONTACT_ENDPOINT;

import com.foundation.salesforce.core.utils.EndPoints;
import groovy.json.JsonOutput;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Class let build endpoint.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public final class ContactApi {
    private RestClientApi restClientApi;
    private Response response;

    /**
     * Class constructor.
     */
    public ContactApi() {
        restClientApi = RestClientApi.getInstance();
        restClientApi.setRequest(Authentication.requestSpecification());
    }

    public static ContactApi getInstance() {
        return new ContactApi();
    }

    public void setContent(String taskBody) {
        restClientApi.buildSpec(taskBody);
    }

    public void setContent(Map taskBody) {

        restClientApi.buildSpec(taskBody);
//        response = restClientApi.get(CONTACT_ENDPOINT + "/" + "{"lastname":"menacho"}");
//        response.prettyPrint();
    }
}
