/*
 * @(#) accessToken.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.common;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.Map;

/**
 * Class that allows to load the result of the salesforce page.
 *
 * @author Jesus Menacho.
 * @version 1.0.
 */
public final class AccessToken {
    private static AccessToken accessToken;
    private static Response response;
    private static Map mapJson;

    /**
     * Constructor let init the method initAccessToken.
     */
    private AccessToken() {
        initAccessToken();
    }

    /**
     * Method let get the instance to the class Accesstoken.
     *
     * @return AccessToken.
     */
    public static AccessToken getInstance() {
        if (accessToken == null) {
            accessToken = new AccessToken();
        }
        return accessToken;
    }

    /**
     * Method that loads the values of the page.
     */
    private static void initAccessToken() {
        String urlAuth = "https://login.salesforce.com/services/oauth2/token";
        response = given()
                .param("grant_type", "password")
                .param("client_id", Util.getInstance().getConfig().getClientID())
                .param("client_secret", Util.getInstance().getConfig().getClientSecret())
                .param("username", Util.getInstance().getConfig().getUserName())
                .param("password", Util.getInstance().getConfig().getPassword()
                        + Util.getInstance().getConfig().getToken())
                .when()
                .post(urlAuth);
        mapJson = response.jsonPath().getMap("$");
    }

    /**
     * Method return the response.
     *
     * @return mapJason.
     */
    public Map getResponse() {
        return mapJson;
    }
}
