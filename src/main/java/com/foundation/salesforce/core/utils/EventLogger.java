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
 * EventLogger
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class EventLogger {
    private static Logger logger = Logger.getLogger(EventLogger.class.getName());

    public static void setLevel(Level level) {
        logger.setLevel(level);
    }

    public static void debug(String message) {
        logger.info(message);
    }

    public static void debug(String message, Throwable t) {
        logger.info(message, t);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String message, Throwable t) {
        logger.info(message, t);
    }

    public static void warn(String message) {
        logger.info(message);
    }

    public static void warn(String message, Throwable t) {
        logger.info(message, t);
    }

    public static void error(String message) {
        logger.info(message);
    }

    public static void error(String message, Throwable t) {
        logger.info(message, t);
    }

    public static void fatal(String message) {
        logger.info(message);
    }

    public static void fatal(String message, Throwable t) {
        logger.info(message, t);
    }
}
