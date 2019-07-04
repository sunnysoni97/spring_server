package com.main.app.server_app.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.app.server_app.common.consoleLogger;

@Controller
public class homeController {
    
    private consoleLogger cl = new consoleLogger();
    
    @GetMapping("/")
    public String returnHomePage(@RequestParam(name="user_name", required=false, defaultValue="Random User") String name){
        cl.info("Entered into homeController- returnHomePage()");
        cl.info("----------------------------------------");
        cl.info(name + " entered the server!");
        cl.info("----------------------------------------");
        return "index.html";
    }

}

