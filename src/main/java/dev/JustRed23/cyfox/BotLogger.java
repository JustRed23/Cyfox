package dev.JustRed23.cyfox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotLogger.class);

    static void info(String msg) {
        LOGGER.info(msg);
    }

    static void info(String formattedMsg, Object... args) {
        LOGGER.info(formattedMsg, args);
    }

    static void warn(String msg) {
        LOGGER.warn(msg);
    }

    static void warn(String formattedMsg, Object... args) {
        LOGGER.warn(formattedMsg, args);
    }

    static void error(String msg) {
        LOGGER.error(msg);
    }

    static void error(String formattedMsg, Object... args) {
        LOGGER.error(formattedMsg, args);
    }

    static void debug(String msg) {
        LOGGER.debug(msg);
    }

    static void debug(String formattedMsg, Object... args) {
        LOGGER.debug(formattedMsg, args);
    }
}
