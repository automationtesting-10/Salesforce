/*
 * @(#) Opportunity.java Copyright (c) 2019 Jala Foundation.
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
 * Opportunity class.
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class Opportunity {
    private String nameOpportunity;
    private String id;

    /**
     * Gets the name of the Opportunity.
     *
     * @return the id as string.
     */
    public String getNameOpportunity() {
        return nameOpportunity;
    }

    /**
     * Sets the name of Opportunity.
     *
     * @param nameOpportunity for the opportunity.
     */

    public void setNameOpportunity(String nameOpportunity) {
        this.nameOpportunity = nameOpportunity;
    }

    /**
     * Gets the id of the Opportunity.
     *
     * @return the id as string.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of Opportunity.
     *
     * @param id for the opportunity.
     */
    public void setId(String id) {
        this.id = id;
    }
}
