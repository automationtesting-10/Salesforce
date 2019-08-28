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
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ResponseValidation performs json schema validation against a json spec stored in resources directory.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public final class ResponseValidation {
    private static ResponseValidation responseValidation;

    /**
     * Private constructor according to singleton pattern.
     */
    private ResponseValidation() {
    }

    /**
     * A publicly accessible method that returns the same instance of this class.
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
     *
     * @param schemaTypeName A string containing the words that, once stripping spaces, will make for the real name
     *                       of json spec.
     * @param response A RestAssured.Response structure resulting from a previously http request call.
     * @return true if json response provided has passed validation against json spec file.
     */
    public boolean matchesJsonSchema(String schemaTypeName, Response response) {
        schemaTypeName = parseSchemaName(schemaTypeName);
        InputStream inputStream = null;
        List violations = new ArrayList<>();
        boolean result;
        try {
            inputStream = getClass().getClassLoader()
                    .getResourceAsStream("schemas/" + schemaTypeName.concat(".json"));
        } catch (NullPointerException npex) {
            EventLogger.error(violations.toString(), npex);
        }
        JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
        Schema schema = SchemaLoader.load(rawSchema);
        try {
            Map map = response.jsonPath().getMap("$");
            JSONObject toBeChecked = new JSONObject(map);
            schema.validate(toBeChecked);
            result = true;
        } catch (ClassCastException ccex) {
            try {
                List list = response.jsonPath().getList("$");
                JSONArray intermeditate = new JSONArray(list);
                schema.validate(intermeditate);
                result = true;
            } catch (ValidationException vex) {
                violations = vex.getCausingExceptions().stream().map(ValidationException::getMessage)
                        .collect(Collectors.toList());
                EventLogger.error(violations.toString(), vex);
                result = false;
            }
        } catch (ValidationException vex) {
            violations = vex.getCausingExceptions().stream().map(ValidationException::getMessage)
                    .collect(Collectors.toList());
            EventLogger.error(violations.toString(), vex);
            result = false;
        }
        try {
            inputStream.close();
        } catch (IOException ioex) {
            EventLogger.error(ioex.getMessage(), ioex);
        }
        return result;
    }

    /**
     * This method expects a space-separated string and parses it so it returns the string in Pascal case format.
     *
     * @param schemaTypeName A string containing the words that, once stripping spaces, will make for the real name
     *                       of json spec.
     * @return A Pascal case-formatted string.
     */
    private String parseSchemaName(String schemaTypeName) {
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

        return schemaTypeName;
    }
}
