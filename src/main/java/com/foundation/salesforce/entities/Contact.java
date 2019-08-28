/*
 * @(#) Contact.java Copyright (c) 2019 Jala Foundation.
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
 * Class where the values of the fields required to create a sales activity are stored.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class Contact {
    private String lastName;
    private String firstName;
    private String email;
    private String title;
    private String id;
    /**
     * Method to return the lastName from the user.
     *
     * @return lastName the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method to let lastName from the user.
     *
     * @param lastName the last name to the user..
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method to let the return the firstName from the first name.
     *
     * @return firstName variable to return the last name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method to let the firstName to the user.
     *
     * @param firstName the last name parameter.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method to let get the email form the user.
     *
     * @return getEmail email to the user return.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to let get a email from the user.
     *
     * @param email the user email parameter.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to let return the title to the user.
     *
     * @return Title from the user parameter.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method to let a title to the user.
     *
     * @param title the title that had the user parameter.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method return id to the user.
     *
     * @return id to the user.
     */
    public String getId() {
        return id;
    }

    /**
     * Method to let send the parameter id.
     *
     * @param id parameter from the user.
     */
    public void setId(String id) {
        this.id = id;
    }
}
