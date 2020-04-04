package com.origincat.oj.controller;

import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.servlet.impl.SelectOJUserServletImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailCheckController {

    private SelectOJUserServletImpl signUpSelectOJUserServlet;

    @Autowired
    public EmailCheckController(SelectOJUserServletImpl signUpSelectOJUserServlet) {
        this.signUpSelectOJUserServlet = signUpSelectOJUserServlet;
    }

    @ResponseBody
    @RequestMapping(value = "/signUp/emailCheck", method = RequestMethod.POST)
    public Map<String, Object> emailCheck(@RequestBody Map<String, Object> params) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String emailStr = params.get("userMail").toString();
        OJUser ojUser = new OJUser();
        ojUser.setUserMail(emailStr);

        if (signUpSelectOJUserServlet.checkOJUser(ojUser)) {
            modelMap.put("success", false);
            modelMap.put("msg", "邮箱已注册");
        } else {
            modelMap.put("success", true);
            modelMap.put("msg", "未被注册");
        }

        return modelMap;
    }


}
