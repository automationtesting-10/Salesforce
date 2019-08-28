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
     * Method let set contact body that let build request.
     *
     * @param contactBody parameter return the contact the body json.
     */
    public void setContent(String contactBody) {
        restClientApi.buildSpec(contactBody);
    }

    /**
     * Method let set contact body that let build request.
     *
     * @param contactBody parameter that contain the contact body json.
     */
    public void setContent(Map contactBody) {

        restClientApi.buildSpec(contactBody);
    }

    /**
     * Method to let delete account to end point.
     * @param id uniquely identifies a given contact.
     * @return a RestAssured Response structure as a result of a successful DELETE request.
     */
    public Response deleteContactById(String id) {

        return restClientApi.delete(CONTACT_ENDPOINT.concat("/").concat(id));
    }

    /**
     * Returns a previously created contact specified by its id.
     *
     * @param id uniquely identifies a given contact.
     * @return a RestAssured Response structure containing the values for all the keys associated to a given contact.
     */
    public Response findContactById(String id) {

        return restClientApi.get(CONTACT_ENDPOINT.concat("/").concat(id));
    }

    /**
     * Returns a RestAssured Response as a result of a successful POST request.
     *
     * @return Returns a RestAssured Response as a result of a successful PATCH request containing among other
     * things, the id of the newly created Task.
     */
    public Response postContent() {

        return restClientApi.post(CONTACT_ENDPOINT);
    }


    /**
     * Returns a RestAssured Response as a result of a successful PATCH request.
     *
     * @param id uniquely identifies the contact to be updated.
     * @return Returns a RestAssured Response as a result of a successful PATCH request.
     */
    public Response patchContent(String id) {

        return restClientApi.patch(CONTACT_ENDPOINT.concat("/").concat(id));
    }

    /**
     * Method return the response summary.
     * @return endPoint that have the contact endpoint.
     */
    public Response retrieveSummary() {
        return restClientApi.get(CONTACT_ENDPOINT);
    }

    /**
     * Returns a previously created Contact specified by its id.
     *
     * @param contactId uniquely identifies a given contactId.
     * @return a RestAssured Response structure containing the values for all the keys associated to a given Contact.
     */
    public Response findContactsById(String contactId) {
        return restClientApi.get(CONTACT_ENDPOINT.concat("/").concat(contactId));
    }

}
