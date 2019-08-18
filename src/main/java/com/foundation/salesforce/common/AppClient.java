/*
 * @(#) AppClient.java Copyright (c) 2019 Jala Foundation.
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
 * Class let implement the get a set variable to response to web page.
 *
 * @author Jesus Menacho.
 * @version 1.0.
 */
public class AppClient {
    private String accessToken;
    private String instanceUrl;
    private String id;
    private String tokenType;
    private String issuedAt;
    private String signature;

    /**
     * Method let return the access token.
     *
     * @return access_token.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Method let set the access token.
     *
     * @param accessToken variable.
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Method let return instance url.
     *
     * @return instance_url.
     */
    public String getInstanceUrl() {
        return instanceUrl;
    }

    /**
     * Method let set instance url.
     *
     * @param instanceUrl variable.
     */
    public void setInstanceUrl(String instanceUrl) {
        this.instanceUrl = instanceUrl;
    }

    /**
     * Method let return the variable id.
     *
     * @return id variable.
     */
    public String getId() {
        return id;
    }

    /**
     * Method let set variable id.
     *
     * @param id variable.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method return the variable Token type.
     *
     * @return token_type variable.
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Method let set the variable token type.
     *
     * @param tokenType variable.
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * Method return the variable issued at.
     *
     * @return issued_at variable.
     */
    public String getIssuedAt() {
        return issuedAt;
    }

    /**
     * Method let set issued at.
     *
     * @param issuedAt variable.
     */
    public void setIssuedAt(String issuedAt) {
        this.issuedAt = issuedAt;
    }

    /**
     * Method let return the variable signature.
     *
     * @return signature variable.
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Method let set the variable signature.
     *
     * @param signature variable.
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }
}
