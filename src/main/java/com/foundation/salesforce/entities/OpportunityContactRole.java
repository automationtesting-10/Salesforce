/*
 * @(#) OpportunityContactRole.java Copyright (c) 2019 Jala Foundation.
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
 * OpportunityContactRole class.
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class OpportunityContactRole {
    private String nameOpportunityContactRole;
    private String id;

    /**
     * Gets the name of the OpportunityContactRole.
     *
     * @return the id as string.
     */
    public String getNameOpportunityContactRole() {
        return nameOpportunityContactRole;
    }

    /**
     * Sets the name of OpportunityContactRole.
     *
     * @param nameOpportunityContactRole for the OpportunityContactRole.
     */

    public void setNameOpportunityContactRole(String nameOpportunityContactRole) {
        this.nameOpportunityContactRole = nameOpportunityContactRole;
    }

    /**
     * Gets the id of the OpportunityContactRole.
     *
     * @return the id as string.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of OpportunityContactRole.
     *
     * @param id for the OpportunityContactRole.
     */
    public void setId(String id) {
        this.id = id;
    }
}
