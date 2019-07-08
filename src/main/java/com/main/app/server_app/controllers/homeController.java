package com.main.app.server_app.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.app.server_app.common.consoleLogger;

@Controller
public class homeController {
    
    @GetMapping("/")
    public String returnHomePage(@RequestParam(name="user_name", required=false, defaultValue="Random User") String name){
        consoleLogger.info("Entered into homeController- returnHomePage()");
        consoleLogger.info("----------------------------------------");
        consoleLogger.info(name + " entered the server!");
        consoleLogger.info("----------------------------------------");
        return "index.html";
    }

}

