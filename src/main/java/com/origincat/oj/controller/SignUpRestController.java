package com.origincat.oj.controller;

import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.*;

@RestController
public class SignUpRestController {

    @ResponseBody
    @RequestMapping(value = "/signUp/sign")
    public Map<String, Object> signUp(@RequestBody Map<String, Object> request){
        Map<String, Object> modelMap = new HashMap<>();
        String userMail = request.get("userMail").toString();
        String userPassWd = request.get("userPassWd").toString();
        String studentName = request.get("studentName").toString();
        int studentClassID = Integer.parseInt(request.get("studentClassID").toString());
        String studentID = request.get("studentID").toString();

        OJUser ojUser = new OJUser();
        Student student = new Student();

        ojUser.setUserMail(userMail);
        ojUser.setUserPassWd(userPassWd);

        student.setStudentMail(userMail);
        student.setStudentClassID(studentClassID);
        student.setStudentName(studentName);
        student.setStudentID(studentID);



        return modelMap;
    }
}
