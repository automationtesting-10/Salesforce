/*
 * @(#) Parcer.java Copyright (c) 2019 Jala Foundation.
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

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that allows to divide the map which contains the responce of the page of sales force.
 *
 * @author Jesus Menacho.
 * @version 1.0.
 */

public final class Parceo {
    private static Parceo parcer;
    private static AppClient appClient = new AppClient();

    /**
     * Constructor let init the method initAccess.
     */
    private Parceo() {
        initAccess();
    }

    /**
     * Method let get the instance to the class Parcer.
     *
     * @return parcer variable.
     */
    public static Parceo getInstance() {
        if (parcer == null) {
            parcer = new Parceo();
        }
        return parcer;
    }

    /**
     * Method that loads the values of the page.
     */
    private static void initAccess() {
        Map<Integer, String> nombreMap = new HashMap<Integer, String>();

        try {
            nombreMap = AccessToken.getInstance().getResponse();
            appClient.setAccessToken(nombreMap.get("access_token"));
            appClient.setInstanceUrl(nombreMap.get("instance_url"));
            appClient.setId(nombreMap.get("id"));
            appClient.setTokenType(nombreMap.get("token_type"));
            appClient.setIssuedAt(nombreMap.get("issued_at"));
            appClient.setSignature(nombreMap.get("signature"));
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
