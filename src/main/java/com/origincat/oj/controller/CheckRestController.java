package com.origincat.oj.controller;

import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.pojo.Student;
import com.origincat.oj.servlet.SelectOJUserServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CheckRestController {
    private SelectOJUserServlet selectOJUserServlet;

    @Autowired
    public CheckRestController(SelectOJUserServlet selectOJUserServlet) {
        this.selectOJUserServlet = selectOJUserServlet;
    }

    @ResponseBody
    @RequestMapping(value = "/signUp/emailCheck", method = RequestMethod.POST)
    public Map<String, Object> emailCheck(@RequestBody Map<String, Object> params) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String emailStr = params.get("userMail").toString();
        OJUser ojUser = new OJUser();
        ojUser.setUserMail(emailStr);

        if (selectOJUserServlet.checkOJUser(ojUser)) {
            modelMap.put("success", false);
            modelMap.put("msg", "邮箱已注册");
        } else {
            modelMap.put("success", true);
            modelMap.put("msg", "未被注册");
        }

        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/signUp/studentIDCheck", method = RequestMethod.POST)
    public Map<String, Object> studentIDCheck(@RequestBody Map<String, Object> params) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String studentID = params.get("studentID").toString();
        Student student = new Student();
        student.setStudentID(studentID);

        if (selectOJUserServlet.checkStudentID(student)) {
            modelMap.put("success", false);
            modelMap.put("msg", "邮箱已被注册");
        } else {
            modelMap.put("success", true);
            modelMap.put("msg", "未被注册");
        }

        return modelMap;
    }
}
