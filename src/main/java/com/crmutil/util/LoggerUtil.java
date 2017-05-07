package com.crmutil.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by My on 2017/5/7.
 */
public class LoggerUtil{
    private Logger logger;

    public LoggerUtil(Logger logger) {
        this.logger = logger;
    }

    public LoggerUtil(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public static LoggerUtil getLogger(Class<?> clazz) {
        return new LoggerUtil(clazz);
    }

    public void info(String message) {
        this.logger.info(message);
    }

    public void info(String message, Throwable e) {
        this.logger.info(message, e);
    }

    public void debug(String message) {
        this.logger.debug(message);
    }

    public void debug(String message, Throwable e) {
        this.logger.debug(message, e);
    }

    public void error(String message) {
        this.logger.error(message);
    }

    public void error(String message, Throwable e) {
        this.logger.error(message, e);
    }

    public void warn(String message) {
        this.logger.warn(message);
    }

    public void warn(String message, Throwable e) {
        this.logger.warn(message, e);
    }

    public void trace(String message) {
        this.logger.trace(message);
    }

    public void trace(String message, Throwable e) {
        this.logger.trace(message, e);
    }
}
