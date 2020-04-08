package com.origincat.oj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origincat.oj.dao.OJUserDao;
import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.pojo.Question;
import com.origincat.oj.pojo.Student;
import com.origincat.oj.pojo.StudentClass;
import com.origincat.oj.servlet.OJUserServlet;
import com.origincat.oj.servlet.QuestionServlet;
import com.origincat.oj.servlet.StudentClassServlet;
import com.origincat.oj.servlet.StudentServlet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private QuestionServlet questionServlet;
    private OJUserServlet ojUserServlet;
    private StudentServlet studentServlet;
    private StudentClassServlet studentClassServlet;

    public AdminController(QuestionServlet questionServlet, OJUserServlet ojUserServlet, StudentServlet studentServlet, StudentClassServlet studentClassServlet){

        this.questionServlet = questionServlet;
        this.ojUserServlet = ojUserServlet;
        this.studentServlet = studentServlet;
        this.studentClassServlet = studentClassServlet;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String adminIndex(Model model, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "status", defaultValue = "0") int status){

        PageHelper.startPage(start,size,"questionID desc");
        List<Question> questionList = null;
        if(status == 0){
            questionList = questionServlet.selectAllQuestion();
        }else {
            questionList = questionServlet.selectQuestionByStatus(status);
        }
        PageInfo<Question> page = new PageInfo(questionList);
        model.addAttribute("page", page);
        model.addAttribute("status", status);

        return "admin/index";
    }

    @RequestMapping(value = "/createQuestion", method = RequestMethod.GET)
    public String adminCreateQuestion(){
        return "admin/createQuestion";
    }

    @RequestMapping(value = "/editQuestion", method = RequestMethod.GET)
    public String adminEditQuestion(Model model, @RequestParam(value = "questionID") String questionID){
        Question question = questionServlet.selectQuestionByID(questionID);
        model.addAttribute("question", question);

        return "admin/editQuestion";
    }

    @RequestMapping(value = "/viewQuestion", method = RequestMethod.GET)
    public String adminViewQuestion(Model model, @RequestParam(value = "questionID") String questionID){
        Question question = questionServlet.selectQuestionByID(questionID);
        model.addAttribute("question", question);

        return "admin/viewQuestion";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "10") int size){

        PageHelper.startPage(start,size,"userID desc");
        List<OJUser> ojUserList = ojUserServlet.selectAllOJUser();
        PageInfo<Question> page = new PageInfo(ojUserList);
        model.addAttribute("page", page);

        return "admin/user";
    }

    @RequestMapping(value = "/auditUser", method = RequestMethod.GET)
    public String auditUser(Model model, @RequestParam(value = "userMail") String userMail){

        Student student = studentServlet.selectStudentByMail(userMail);
        StudentClass studentClass = studentClassServlet.selectStudentClassByID(student.getStudentClassID());
        model.addAttribute("student", student);
        model.addAttribute("studentClass", studentClass);

        return "admin/auditUser";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submit(Model model){
        return "admin/submit";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model){
        return "admin/about";
    }
}
