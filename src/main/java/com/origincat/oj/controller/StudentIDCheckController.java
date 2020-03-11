package com.origincat.oj.controller;

import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.pojo.Student;
import com.origincat.oj.servlet.impl.SignUpSelectOJUserServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentIDCheckController {
    private SignUpSelectOJUserServlet signUpSelectOJUserServlet;

    @Autowired
    public StudentIDCheckController(SignUpSelectOJUserServlet signUpSelectOJUserServlet) {
        this.signUpSelectOJUserServlet = signUpSelectOJUserServlet;
    }

    @ResponseBody
    @RequestMapping(value = "/signUp/studentIDCheck", method = RequestMethod.POST)
    public Map<String, Object> emailCheck(@RequestBody Map<String, Object> params) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String studentID = params.get("studentID").toString();
        Student student = new Student();
        student.setStudentID(studentID);

        if (signUpSelectOJUserServlet.checkStudentID(student)) {
            modelMap.put("success", false);
            modelMap.put("msg", "邮箱已被注册");
        } else {
            modelMap.put("success", true);
            modelMap.put("msg", "未被注册");
        }

        return modelMap;
    }
}
