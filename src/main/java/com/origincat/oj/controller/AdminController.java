package com.origincat.oj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origincat.oj.dao.OJUserDao;
import com.origincat.oj.pojo.*;
import com.origincat.oj.servlet.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private QuestionServlet questionServlet;
    private OJUserServlet ojUserServlet;
    private StudentServlet studentServlet;
    private StudentClassServlet studentClassServlet;
    private JudgeServlet judgeServlet;

    public AdminController(QuestionServlet questionServlet, OJUserServlet ojUserServlet,
                           StudentServlet studentServlet, StudentClassServlet studentClassServlet,
                           JudgeServlet judgeServlet){

        this.questionServlet = questionServlet;
        this.ojUserServlet = ojUserServlet;
        this.studentServlet = studentServlet;
        this.studentClassServlet = studentClassServlet;
        this.judgeServlet = judgeServlet;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String adminIndex(Model model, @RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "status", defaultValue = "0") int status){

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
    public String user(Model model, @RequestParam(value = "start", defaultValue = "1") int start,
                       @RequestParam(value = "size", defaultValue = "10") int size){

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
    public String submit(Model model, @RequestParam(value = "start", defaultValue = "1") int start,
                         @RequestParam(value = "size", defaultValue = "10") int size,
                         @RequestParam(value = "status", defaultValue = "0") int status,
                         HttpServletRequest request){
        StringBuffer buffer = new StringBuffer();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userMail")){
                    buffer.append(cookie.getValue());
                }
            }
        }
        String userMail = buffer.toString();
        PageHelper.startPage(start,size,"submitID desc");
        List<JudgeResult> judgeResultList = null;
        if(status == 0){
            judgeResultList = judgeServlet.selectJudge();
        }else if(status == 2){
            judgeResultList = judgeServlet.selectACJudge();
        }else if(status == 1){
            judgeResultList = judgeServlet.selectNoACJudge();
        }
        PageInfo<JudgeResult> page = new PageInfo(judgeResultList);
        model.addAttribute("page", page);
        model.addAttribute("status", status);

        return "admin/submit";
    }

    @RequestMapping(value = "/viewSubmit", method = RequestMethod.GET)
    public String viewSubmit(Model model, @RequestParam(value = "submitID") String submitID){
        JudgeResult judgeResult = judgeServlet.selectJudgeBySubmitID(submitID);
        Question question = questionServlet.selectQuestionByNum(judgeResult.getQuestionNum());
        model.addAttribute("submit", judgeResult);
        model.addAttribute("question", question);

        return "admin/viewSubmit";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model){
        return "admin/about";
    }
}
