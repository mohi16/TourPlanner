package org.easytours.tourplanner.logging;

import org.easytours.tpmodel.logging.LoggerFactory;
import org.easytours.tpmodel.logging.LoggerWrapper;

public class LogManager {
    private static final LoggerWrapper LOGGER = LoggerFactory.getLogger();

    public static LoggerWrapper getLogger() {
        return LOGGER;
    }
}
