/*
 * @(#) Config.java Copyright (c) 2019 Jala Foundation.
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

/**
 * Class let set and get variable from application.properties.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class Config {
    private String clientID;
    private String clientSecret;
    private String redirectUri;
    private String userName;
    private String password;
    private String token;

    /**
     * Method return id client.
     * @return clientID variable.
     */
    public String getClientID() {
        return clientID;
    }

    /**
     * Method let set the variable id client.
     * @param clientID set a variable client id.
     */
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * Method return the client key secret.
     * @return clientScret variable.
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Method let set the key client secret.
     * @param clientSecret variable.
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * Method return the variable Redirect Uri.
     * @return redirectUri variable return.
     */
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * Method to let to set redirect Uri.
     * @param redirectUri variable to server to redirect.
     */
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    /**
     * Method to return the variable user name.
     * @return userName variable.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Method let set the user name.
     * @param userName variable.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Method to let get password to the user.
     * @return password variable.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method let set password to the user.
     * @param password variable.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method let set token variable.
     * @return token variable.
     */
    public String getToken() {
        return token;
    }

    /**
     * Method let set token variable.
     * @param token variable
     */
    public void setToken(String token) {
        this.token = token;
    }
}
