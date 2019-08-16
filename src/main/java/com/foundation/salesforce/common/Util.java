/*
 * @(#) Util.java Copyright (c) 2019 Jala Foundation.
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

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Util class implements the singleton which allows to read the application properties.
 *
 * @author Jesus Menacho.
 * @version 1.0.
 */
public final class Util {
    private static Util utilCreation;
    private static Config config = new Config();

    /**
     * This constructor let me initializer the method initUnit.
     */
    private Util() {
        initUtil();
    }

    /**
     * Method let return the instance Util class.
     *
     * @return Util variable class.
     */
    public static Util getInstance() {
        if (utilCreation == null) {
            utilCreation = new Util();
        }
        return utilCreation;
    }

    /**
     * Method init the class util.
     */
    private static void initUtil() {

        try (InputStream input = new FileInputStream(System.getProperty("user.dir") + "/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            config.setClientID(properties.getProperty("app.client_id"));
            config.setClientSecret(properties.getProperty("app.client_secret"));
            config.setUserName(properties.getProperty("app.user_name"));
            config.setPassword(properties.getProperty("app.password"));
            config.setToken(properties.getProperty("app.security_token"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method let obtain the class Config for access to variables.
     *
     * @return Config the class, constains all variable required in DB connections.
     */
    public Config getConfig() {
        return config;
    }
}
