/*
 * @(#) Builder.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.util;

import com.foundation.salesforce.common.Parceo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * Class that allows to build a direction to be sent to the page of sales force.
 *
 * @author Jesus Menacho.
 * @version 1.0.
 */
public class Builder {
    /**
     * Constructor.
     */
    public Builder() {
    }

    /**
     * Method that allows to build an address and then be sent to the page of sales force according to a token.
     *
     * @return RequestSpecification variable.
     */
    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(Parceo.getInstance().containtReq().getInstanceUrl())
                .addHeader("Authorization", Parceo.getInstance().containtReq().getTokenType()
                        + " "
                        + Parceo.getInstance().containtReq().getAccessToken())
                .build();
    }
}
