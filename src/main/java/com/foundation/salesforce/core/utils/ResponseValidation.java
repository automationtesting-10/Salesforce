/*
 * @(#) ResponseValidation.java Copyright (c) 2019 Jala Foundation.
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

import io.restassured.response.Response;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

/**
 * ResponseValidation performs json schema validation against a json spec stored in resources directory.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
final public class ResponseValidation {
    private static ResponseValidation responseValidation;

    /**
     * Private constructor according to singleton pattern.
     */
    private ResponseValidation() {

    }

    /**
     *
     * @return an the same instance over and over again upon every call.
     */
    public static ResponseValidation getInstance() {
        if (responseValidation == null) {
            responseValidation = new ResponseValidation();
        }
        return responseValidation;
    }

    /**
     * Returns true if json response validation is successful.
     *
     * @param schemaTypeName A string containing the words that, once stripping spaces, will make for the real name
     *                       of json spec.
     * @param response A RestAssured.Response structure resulting from a previously http request call.
     * @return true if json response provided has passed validation against json spec file.
     */
    public boolean matchesJsonSchema(String schemaTypeName, Response response) {
        StringBuilder stringAccumulator = new StringBuilder();
        char currentCharacter = ' ';
        for (int characterIterator = 0; characterIterator < schemaTypeName.length(); characterIterator++) {
            if (currentCharacter == ' ' && schemaTypeName.charAt(characterIterator) != ' ') {
                stringAccumulator.append(Character.toUpperCase(schemaTypeName.charAt(characterIterator)));
            } else {
                stringAccumulator.append(schemaTypeName.charAt(characterIterator));
            }
            currentCharacter = schemaTypeName.charAt(characterIterator);
        }
        schemaTypeName = stringAccumulator.toString().replaceAll("\\s", "").trim();

        InputStream inputStream = null;
        try  {
            inputStream = getClass().getClassLoader().getResourceAsStream(schemaTypeName.concat(".json"));
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(new JSONObject(response.jsonPath().getMap("$")));
            return true;
        } catch (ValidationException npvex) {
            return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
