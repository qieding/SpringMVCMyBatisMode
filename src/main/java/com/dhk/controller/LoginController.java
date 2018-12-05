package com.dhk.controller;

import com.dhk.controller.validation.ValidGroup1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping(value = "loginPage")
    public String loginPage()throws Exception{
        return "login";
    }
    @RequestMapping(value = "loginAction",method = {RequestMethod.POST})
    public String login(HttpSession httpSession,String username)throws Exception{
        System.out.println("username:::"+username);
        httpSession.setAttribute("username",username);
//        return "forward:oklogin";
        return "forward:/WEB-INF/views/ok.html";
//        return "/WEB-INF/views/ok.html";
    }
    @RequestMapping(value = "oklogin")
    public String ok( )throws Exception{
        return "ok";
    }
    @RequestMapping(value = "logout")
    public String logout(HttpSession httpSession)throws Exception{
        httpSession.invalidate();
        return "error";
    }

}
