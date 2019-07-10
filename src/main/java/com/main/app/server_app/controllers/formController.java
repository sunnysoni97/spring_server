package com.main.app.server_app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import com.main.app.server_app.common.consoleLogger;
import com.main.app.server_app.common.passMethods;
import com.main.app.server_app.dao.UserOp;
import com.main.app.server_app.models.EntryStatus;
import com.main.app.server_app.models.User;
import com.main.app.server_app.processors.ValidateNewUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes("user")
public class formController {

    @RequestMapping(value = "/new_user", method = { RequestMethod.GET })
    public String serveForm(Model model,
            @RequestParam(name = "entry_code", required = false, defaultValue = "0") int entryCode) {
        consoleLogger.info("Entered into formController - serveForm()");
        consoleLogger.info("serveForm entry code = " + entryCode);
        model.addAttribute("entryStatus", new EntryStatus(entryCode));
        model.addAttribute("user", new User());
        return "user_reg_form";
    }

    @RequestMapping(value = "/register_details", method = { RequestMethod.GET })
    public String serveDetailsForm(@ModelAttribute User user) {
        consoleLogger.info("Entered into formController - serveDetailsForm()");
        consoleLogger.info("Username - " + user.getUserName());
        return "user_det_form";
    }

    @RequestMapping(value = "/view_details", method = { RequestMethod.GET })
    public String serveDetails(@ModelAttribute User user) {
        consoleLogger.info("Entered into formController - serveDetails()");
        return "view_details_page";
    }

}

@Controller
@SessionAttributes("user")
class formControllerPosts {

    @RequestMapping(value = "/logout", method = { RequestMethod.POST })
    public void logout(HttpServletResponse httpServletResponse, SessionStatus sessionStatus) throws IOException {
        consoleLogger.info("Entered into formControllerPosts - logout()");
        sessionStatus.setComplete();
        httpServletResponse.sendRedirect("");
    }

    @RequestMapping(value = "/new_user", method = { RequestMethod.POST })
    public void serveResults(@ModelAttribute User user, HttpServletResponse httpServletResponse,
            SessionStatus sessionStatus) throws IOException {
        consoleLogger.info("Entered into formControllerPosts - serveResults()");
        ValidateNewUser vnu = new ValidateNewUser();
        int flag = vnu.checkData(user);
        if (flag == 0) {
            user.setUserPass(passMethods.encrypt(user.getUserPass()));
            UserOp userOp = new UserOp();
            if (userOp.registerUser(user))
                httpServletResponse.sendRedirect("register_details");
            else
            {
                flag = 1;
                sessionStatus.setComplete();
                httpServletResponse.sendRedirect("new_user?entry_code=" + flag);
            }
        } else {
            sessionStatus.setComplete();
            httpServletResponse.sendRedirect("new_user?entry_code=" + flag);
        }
    }

    @RequestMapping(value = "/upload_file", method = { RequestMethod.POST })
    public void uploadFile(@RequestParam("file") MultipartFile file, HttpServletResponse httpServletResponse, @ModelAttribute User user) throws IOException {
        consoleLogger.info("Entered into formControllerPosts - uploadFile()");
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("images/" + user.getUserName() + ".png");
                Files.write(path.toAbsolutePath(), bytes);
                consoleLogger.info("Image Successfully uploaded!");
                httpServletResponse.sendRedirect("view_details");
            } catch (IOException e){
                consoleLogger.errorMsg("error while file uploading!");
                e.printStackTrace();
            }
        }
    }

}