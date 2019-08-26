/*
 * @(#) Account.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.entities;

/**
 * Account class.
 *
 * @author John Salazar Pinto
 * @version 1.0
 */
public class Account {
    private String id;

    /**
     * This method get the id of the account.
     *
     * @return id that was used for the scenarios.
     */
    public String getId() {
        return id;
    }

    /**
     * This method set the id ot the account that was used for the scenarios.
     *
     * @param id - set the id of the account obtained.
     */
    public void setId(String id) {
        this.id = id;
    }
}
