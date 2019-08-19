/*
 * @(#) Task.java Copyright (c) 2019 Jala Foundation.
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
 * Models the object Task found within the Salesforce platform.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class Task {
    private String id;
    private String status;
    private String priority;

    /**
     *
     * @return the id of the Task.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of a recently created Task.
     *
     * @param id the String to which this Task will be associated with.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return the status of the Task, which can take the following values:
     *         Not Started.
     *         In Progress.
     *         Completed.
     *         Waiting on someone else.
     *         Deferred.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status that this task will take.
     *
     * @param status : Not Started, In Progress, Completed, Waiting for someone else, Deferred.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return Priority of the Task: Low, Medium, High.
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the priority that this task will take.
     *
     * @param priority Priority of the Task: Low, Medium, High.
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }
}
