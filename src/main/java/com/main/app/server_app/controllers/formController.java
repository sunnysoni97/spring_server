package com.main.app.server_app.controllers;

import com.main.app.server_app.common.consoleLogger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class formController{
    
    private consoleLogger cl = new consoleLogger();
    
    @RequestMapping(value="/random_form", method={RequestMethod.GET})
    public String serveForm(){
        cl.info("Entered into formController - serveForm()");
        return "form.html";
    }

    @RequestMapping(value="/random_form", method={RequestMethod.POST})
    public String serveResults(){
        cl.info("Entered into formController - serveResults()");
        return "redirect:/form_results.html";
    }
}
