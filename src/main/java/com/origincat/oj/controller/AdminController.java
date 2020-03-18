package com.origincat.oj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String adminIndex(){
        return "admin/index";
    }

    @RequestMapping(value = "/createQuestion", method = RequestMethod.GET)
    public String adminCreateQuestion(){
        return "admin/createQuestion";
    }
}
