/*
 * @(#) Context.java Copyright (c) 2019 Jala Foundation.
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
 * Context class
 *
 * @author Cristian Lujan
 * @version 1.0
 */
public class Context {

    /**
     * contact of type opportunity.
     */
    private Opportunity opportunity;


    private Task task;

    /**
     * Context for creation of the constructor.
     */
    public Context() {
        this.opportunity = new Opportunity();
    }

    /**
     * Returns the Opportunity of context.
     *
     * @return opportunity.
     */
    public Opportunity getOpportunity() {
        return opportunity;
    }


    public Task getTask() {
        return task;
    }
}