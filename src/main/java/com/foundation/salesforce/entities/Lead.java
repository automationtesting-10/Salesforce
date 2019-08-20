/*
 * @(#) Lead.java Copyright (c) 2019 Jala Foundation.
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
 * Lead class which saves relevant lead data.
 *
 * @author Melissa Román
 * @version 1.0
 */
public class Lead {
    private String company;
    private String lastName;
    private String id;

    /**
     * Allows to get lead's company.
     *
     * @return Lead's company.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Allows to set lead's company.
     *
     * @param company - Lead's desired company.
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Allows to get lead's last name.
     *
     * @return Lead's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Allows to set lead's last name.
     *
     * @param lastName - Lead's desired last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Allows to get lead's Id.
     *
     * @return - Lead's Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Allows to set lead's desired Id.
     *
     * @param id - Lead's Id.
     */
    public void setId(String id) {
        this.id = id;
    }
}
