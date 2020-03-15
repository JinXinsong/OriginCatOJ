package com.origincat.oj.controller;

import com.origincat.oj.pojo.StudentClass;
import com.origincat.oj.servlet.StudentClassServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    StudentClassServlet studentClassServlet;

    @Autowired
    public IndexController(StudentClassServlet studentClassServlet){
        this.studentClassServlet = studentClassServlet;
    }


    @RequestMapping(value = {"/", "index"})
    public String signUp(Model model){
        List<StudentClass> studentClassList = studentClassServlet.selectAllStudentClass();
        model.addAttribute("studentClassList", studentClassList);
        return "index/index";
    }

    @RequestMapping(value = "/student/index")
    public String studentIndex(){
        return "student/index";
    }

    @RequestMapping(value = "/admin/index")
    public String adminIndex(){
        return "admin/index";
    }
}