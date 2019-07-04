package com.main.app.server_app.common;

import com.main.app.server_app.DemoApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class consoleLogger {
    private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);    
    
    public void info(String msg){
        logger.info(msg);
    }

    public void errorMsg(String msg){
        logger.error(msg);
    }

}