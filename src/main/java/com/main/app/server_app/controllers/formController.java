package com.main.app.server_app.controllers;

import com.main.app.server_app.common.consoleLogger;
import com.main.app.server_app.common.passMethods;
import com.main.app.server_app.models.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class formController{
    
    @RequestMapping(value="/random_form", method={RequestMethod.GET})
    public String serveForm(Model model){
        consoleLogger.info("Entered into formController - serveForm()");
        model.addAttribute("user", new User());
        return "form";
    }

    @RequestMapping(value="/random_form", method={RequestMethod.POST})
    public String serveResults(@ModelAttribute User user){
        consoleLogger.info("Entered into formController - serveResults()");
        consoleLogger.info(user.getUserName());
        user.setUserPass(passMethods.encrypt(user.getUserPass()));
        consoleLogger.info(user.getUserPass());
        return "form_results";
    }
}
