package com.main.app.server_app.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.main.app.server_app.common.consoleLogger;
import com.main.app.server_app.models.EntryStatus;
import com.main.app.server_app.models.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class formController{
    
    @RequestMapping(value="/new_user", method={RequestMethod.GET})
    public String serveForm(Model model, @RequestParam(name="entry_code", required=false, defaultValue="0") int entryCode){
        consoleLogger.info("Entered into formController - serveForm()");
        if(entryCode == 0){
            consoleLogger.info("serveForm entry code = 0");
            model.addAttribute("entryStatus", new EntryStatus(0));
        }
        else
        {
            consoleLogger.info("serveForm entry code = 1");
            model.addAttribute("entryStatus", new EntryStatus(1));
        }
        model.addAttribute("user", new User());
        return "user_reg_form";
    }

    @RequestMapping(value="/new_user", method={RequestMethod.POST})
    public void serveResults(@ModelAttribute User user, HttpServletResponse httpServletResponse) throws IOException{
        boolean flag = true;
        if (flag){
            consoleLogger.info(user.getUserName());
            consoleLogger.info(user.getUserPass());
            httpServletResponse.sendRedirect("register_details?username");
        }
        else
        {
            httpServletResponse.sendRedirect("new_user?entry_code=1");
        }
    }
    
    @RequestMapping(value="/register_details", method={RequestMethod.GET})
    public String serveDetailsForm (@ModelAttribute User user){
        consoleLogger.info(user.getUserName());
        consoleLogger.info(user.getUserPass());
        return "user_det_form";
    }

}