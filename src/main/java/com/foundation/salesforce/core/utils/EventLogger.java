/*
 * @(#) EventLogger.java Copyright (c) 2019 Jala Foundation.
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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * EventLogger performs basic logging operations, and level setup.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class EventLogger {
    private static Logger logger = Logger.getLogger(EventLogger.class.getName());

    /**
     * EventLogger may print or log sensitive operation details to console or a file according to the user's needs
     * and configurations.
     *
     * Private as this is merely a static utility class that is not intended to be instatiated.
     */
    private EventLogger() {
    }

    /**
     * Set the level of this Category.
     *
     * @param level A level recognized by the system, that is OFF, FATAL, ERROR, WARN, INFODEBUG and ALL.
     */
    public static void setLevel(Level level) {
        logger.setLevel(level);
    }

    /**
     * Logs a message object with the DEBUG level.
     *
     * @param message A customized message that briefly summarizes the situation.
     */
    public static void debug(String message) {
        logger.info(message);
    }

    /**
     * Logs a message at the DEBUG level including the stack trace of the Throwable t passed as parameter.
     *
     * @param message A customized message that briefly summarizes the situation.
     * @param t  An instance of Exception class (or one of its subclasses) thrown by the Java Virtual Machine or
     *           by the Java throw statement.
     */
    public static void debug(String message, Throwable t) {
        logger.info(message, t);
    }

    /**
     * Logs a message object with the INFO level.
     *
     * @param message A customized message that briefly summarizes the situation.
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Logs a message at the INFO level including the stack trace of the Throwable t passed as parameter.
     *
     * @param message A customized message that briefly summarizes the situation.
     * @param t  An instance of Exception class (or one of its subclasses) thrown by the Java Virtual Machine or
     *           by the Java throw statement.
     */
    public static void info(String message, Throwable t) {
        logger.info(message, t);
    }

    /**
     * Logs a message object with the WARN level.
     *
     * @param message A customized message that briefly summarizes the situation.
     */
    public static void warn(String message) {
        logger.info(message);
    }

    /**
     * Logs a message at the WARN level including the stack trace of the Throwable t passed as parameter.
     *
     * @param message A customized message that briefly summarizes the situation.
     * @param t  An instance of Exception class (or one of its subclasses) thrown by the Java Virtual Machine or
     *           by the Java throw statement.
     */
    public static void warn(String message, Throwable t) {
        logger.info(message, t);
    }

    /**
     * Logs a message object with the ERROR level.
     *
     * @param message A customized message that briefly summarizes the situation.
     */
    public static void error(String message) {
        logger.info(message);
    }

    /**
     * Logs a message at the ERROR level including the stack trace of the Throwable t passed as parameter.
     *
     * @param message A customized message that briefly summarizes the situation.
     * @param t  An instance of Exception class (or one of its subclasses) thrown by the Java Virtual Machine or
     *           by the Java throw statement.
     */
    public static void error(String message, Throwable t) {
        logger.info(message, t);
    }

    /**
     * Logs a message object with the FATAL level.
     *
     * @param message A customized message that briefly summarizes the situation.
     */
    public static void fatal(String message) {
        logger.info(message);
    }

    /**
     * Logs a message at the FATAL level including the stack trace of the Throwable t passed as parameter.
     *
     * @param message A customized message that briefly summarizes the situation.
     * @param t  An instance of Exception class (or one of its subclasses) thrown by the Java Virtual Machine or
     *           by the Java throw statement.
     */
    public static void fatal(String message, Throwable t) {
        logger.info(message, t);
    }
}
