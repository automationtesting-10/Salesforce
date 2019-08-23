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
    String lastName;
    String firstName;
    String email;
    String Title;

    /**
     * Method to return the lasName from the user.
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method to let lastName from the user.
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method to let the return the firstName from the first name.
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method to let the firstName to the user.
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method to let get the email form the user.
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to let get a email from the user.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to let return the title to the user.
     *
     * @return Title.
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Method to let a title to the user.
     *
     * @param title
     */
    public void setTitle(String title) {
        Title = title;
    }
}
