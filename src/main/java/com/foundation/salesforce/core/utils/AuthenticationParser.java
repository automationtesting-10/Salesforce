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

package com.foundation.salesforce.core.utils;

import org.json.JSONException;

import java.util.Map;

/**
 * Class that allows to divide the map which contains the responce of the page of sales force.
 *
 * @author Jesus Menacho.
 * @version 1.0.
 */

public final class AuthenticationParser {
    private static AuthenticationParser parcer;
    private static AppClient appClient;

    /**
     * Constructor let init the method initAccess.
     */
    private AuthenticationParser() {
        access();
    }

    /**
     * Method let get the instance to the class Parcer.
     *
     * @return parcer variable.
     */
    public static AuthenticationParser getInstance() {
        if (parcer == null) {
            appClient = new AppClient();
            parcer = new AuthenticationParser();
        }
        return parcer;
    }

    /**
     * Method that loads the values of the page.
     */
    private static void access() {

        try {
            Map<String, String> nameMap = AccessToken.getInstance().getResponse();
            appClient.setAccessToken(nameMap.get("access_token"));
            appClient.setInstanceUrl(nameMap.get("instance_url"));
            appClient.setId(nameMap.get("id"));
            appClient.setTokenType(nameMap.get("token_type"));
            appClient.setIssuedAt(nameMap.get("issued_at"));
            appClient.setSignature(nameMap.get("signature"));
            appClient.setAuthUrl(nameMap.get("auth_url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method return the variable to the page.
     *
     * @return mapJason variable..
     */
    public AppClient containtReq() {
        return appClient;
    }
}
