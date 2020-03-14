package com.origincat.oj.controller;

import com.origincat.oj.enums.StudentSignUpEnum;
import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.pojo.Student;
import com.origincat.oj.servlet.StudentServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SignUpRestController {

    private StudentServlet studentServlet;

    @Autowired
    public SignUpRestController(StudentServlet studentServlet){
        this.studentServlet = studentServlet;
    }

    @ResponseBody
    @RequestMapping(value = "/signUp/submit", method = RequestMethod.POST)
    public Map<String, Object> signUp(@RequestBody Map<String, Object> request, HttpServletResponse response){
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

        if(studentServlet.insertStudent(student, ojUser) == StudentSignUpEnum.CHECK){
            Cookie cookie = new Cookie("userMail", ojUser.getUserMail());
            response.addCookie(cookie);
            modelMap.put("success", true);
            modelMap.put("msg", "注册成功");
        }else{
            modelMap.put("success", false);
            modelMap.put("msg", "注册失败 请检测注册信息或联系管理员");
        }

        return modelMap;
    }
}
