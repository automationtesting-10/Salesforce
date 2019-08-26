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

import io.restassured.response.Response;

import java.util.Map;

import static com.foundation.salesforce.core.utils.EndPoints.CONTACT_ENDPOINT;

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

    /**
     * Method let get the new instance to the class.
     *
     * @return ContactApi the variable return the contact.
     */
    public static ContactApi getInstance() {
        return new ContactApi();
    }

    /**
     * Method let set task body that let build request.
     *
     * @param Contactbody
     */
    public void setContent(String Contactbody) {
        restClientApi.buildSpec(Contactbody);
    }

    /**
     * Method let set task body that let build request.
     *
     * @param Contactbody
     */
    public void setContent(Map Contactbody) {

        restClientApi.buildSpec(Contactbody);
    }

    /**
     * Method to let delete account to end point.
     * @param id uniquely identifies a given Task.
     * @return a RestAssured Response structure as a result of a successful DELETE request.
     */
    public Response deleteTaskById(String id) {

        return restClientApi.delete(CONTACT_ENDPOINT.concat("/").concat(id));
    }

    /**
     * Returns a previously created Task specified by its id.
     *
     * @param id uniquely identifies a given Task.
     * @return a RestAssured Response structure containing the values for all the keys associated to a given Task.
     */
    public Response findTaskById(String id) {
        return restClientApi.get(CONTACT_ENDPOINT.concat("/").concat(id));
    }

    /**
     * Returns an overview of Task's metadata as well as a list of the most recently used Task records.
     *
     * @return a RestAssured Response structure containing metada of the most recently used Task records.
     */
    public Response retrieveSummaryForTask() {
        return restClientApi.get(CONTACT_ENDPOINT);
    }

}
