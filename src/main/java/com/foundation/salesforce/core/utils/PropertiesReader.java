/*
 * @(#) PropertiesReader.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class implements the singleton which allows to read the application properties.
 *
 * @author Jesus Menacho.
 * @version 1.0.
 */
public final class PropertiesReader {
    private static PropertiesReader propertiesReaderCreation;
    private static ConfigParameters configParameters;

    /**
     * This constructor let me initializer the method initUnit.
     */
    private PropertiesReader() {
        initUtil();
    }

    /**
     * Method let return the instance Util class.
     *
     * @return Util variable class.
     */
    public static PropertiesReader getInstance() {
        if (propertiesReaderCreation == null) {
            configParameters = new ConfigParameters();
            propertiesReaderCreation = new PropertiesReader();
        }
        return propertiesReaderCreation;
    }

    /**
     * Method init the class util.
     */
    private static void initUtil() {
        try (InputStream input = new FileInputStream(System.getProperty("user.dir") + "/gradle.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            configParameters.setClientID(properties.getProperty("client_id"));
            configParameters.setClientSecret(properties.getProperty("client_secret"));
            configParameters.setUserName(properties.getProperty("user_name"));
            configParameters.setPassword(properties.getProperty("password"));
            configParameters.setToken(properties.getProperty("security_token"));
            configParameters.setAuthUrl(properties.getProperty("auth_url"));
            configParameters.setTester_name(properties.getProperty("tester_name"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method let obtain the class ConfigParameters for access to variables.
     *
     * @return ConfigParameters the class, constains all variable required in DB connections.
     */
    public ConfigParameters getConfig() {
        return configParameters;
    }
}
