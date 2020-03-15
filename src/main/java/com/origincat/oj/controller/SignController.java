package com.origincat.oj.controller;

import com.origincat.oj.enums.OJUserSignInEnum;
import com.origincat.oj.enums.StudentSignUpEnum;
import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.pojo.Student;
import com.origincat.oj.servlet.SelectOJUserServlet;
import com.origincat.oj.servlet.StudentServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SignController {

    private StudentServlet studentServlet;
    private SelectOJUserServlet selectOJUserServlet;

    @Autowired
    public SignController(StudentServlet studentServlet, SelectOJUserServlet selectOJUserServlet){
        this.studentServlet = studentServlet;
        this.selectOJUserServlet = selectOJUserServlet;
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

    @ResponseBody
    @RequestMapping(value = "/signIn/submit", method = RequestMethod.POST)
    public Map<String, Object> signIn(@RequestBody Map<String, Object> requestMap, HttpServletResponse response){
        Map<String, Object> modelMap = new HashMap<>();
        String userMail = requestMap.get("userMail").toString();
        String userPassWd = requestMap.get("userPassWd").toString();

        OJUser ojUser = new OJUser();

        ojUser.setUserMail(userMail);
        ojUser.setUserPassWd(userPassWd);

        OJUserSignInEnum ojUserSignInEnum = selectOJUserServlet.checkOJUserSignIn(ojUser);
        switch (ojUserSignInEnum){
            case CHECK:{
                modelMap.put("success", true);
                modelMap.put("result" , "check");
                break;
            }
            case ADMIN:{
                modelMap.put("success", true);
                modelMap.put("result", "admin");
                break;
            }
            case ERROR:{
                modelMap.put("success", false);
                modelMap.put("result", "error");
                break;
            }
            case STUDENT:{
                modelMap.put("success", true);
                modelMap.put("result", "student");
                break;
            }
            default:{
                modelMap.put("success", false);
                modelMap.put("result", "unKnowError");
            }
        }
        return modelMap;
    }
}
