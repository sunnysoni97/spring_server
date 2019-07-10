package com.main.app.server_app.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;

import com.main.app.server_app.common.consoleLogger;

@Controller
public class homeController {
    
    @GetMapping("/")
    public String returnHomePage(){
        consoleLogger.info("Entered into homeController- returnHomePage()");
        return "index";
    }

}

