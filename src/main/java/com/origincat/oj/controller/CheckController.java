package com.origincat.oj.controller;

import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.servlet.SelectOJUserServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class CheckController {
    private SelectOJUserServlet selectOJUserServlet;

    @Autowired
    public CheckController(SelectOJUserServlet selectOJUserServlet) {
        this.selectOJUserServlet = selectOJUserServlet;
    }

    @RequestMapping(value = "/index/check")
    public String indexCheck(Model model, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        OJUser ojUser = new OJUser();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userMail")){
                    ojUser.setUserMail(cookie.getValue());
                    ojUser = selectOJUserServlet.selectOJUser(ojUser);
                }
            }
        }
        model.addAttribute("studentName", ojUser.getUserName());

        return "index/check";
    }
}
