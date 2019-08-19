/*
 * @(#) ReaderDriverProperties.java Copyright (c) 2019 Jala Foundation.
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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ReaderDriverProperties class.
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class ReaderDriverProperties {

    private static final String URL_SALESFORCE_PROPERTIES = "gradle.properties";

    /**
     * Map to save the properties of the application.
     */
    private Map<String, String> properties;

    protected ReaderDriverProperties() {
        properties = new HashMap<>();
        addPropertiesSalesForce();
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void addPropertiesSalesForce() {
        Properties propertiesSalesForce = loadFile(URL_SALESFORCE_PROPERTIES);
        propertiesSalesForce.forEach((key, value) -> properties.put(key.toString(), value.toString()));
    }

    public static ReaderDriverProperties getInstance() {
        return new ReaderDriverProperties();
    }

    private Properties loadFile(String url) {
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream(url);
            // load a properties file
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}