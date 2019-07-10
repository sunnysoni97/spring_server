package com.main.app.server_app.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.main.app.server_app.common.consoleLogger;
import com.main.app.server_app.models.EntryStatus;
import com.main.app.server_app.models.User;
import com.main.app.server_app.processors.ValidateNewUser;

import org.apache.commons.lang3.mutable.MutableBoolean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@SessionAttributes("user")
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
    
    @RequestMapping(value="/register_details", method={RequestMethod.GET})
    public String serveDetailsForm (@ModelAttribute User user){
        consoleLogger.info("Entered into formController - serveDetailsForm()");
        consoleLogger.info("Username - " + user.getUserName());
        return "user_det_form";
    }

}

@Controller
@SessionAttributes("user")
class formControllerPosts{
    
    @RequestMapping(value="/new_user", method={RequestMethod.POST})
    public void serveResults(@ModelAttribute User user, HttpServletResponse httpServletResponse, SessionStatus sessionStatus) throws IOException{
        consoleLogger.info("Entered into formControllerPosts - serveResults()");
        MutableBoolean flag = new MutableBoolean(false);
        ValidateNewUser.checkData(flag, user);
        if (flag.isTrue()){
            httpServletResponse.sendRedirect("register_details");
        }
        else
        {
            sessionStatus.setComplete();
            httpServletResponse.sendRedirect("new_user?entry_code=1");
        }
    }

}