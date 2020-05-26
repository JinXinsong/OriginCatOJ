package com.origincat.oj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origincat.oj.pojo.*;
import com.origincat.oj.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    private QuestionServlet questionServlet;
    private JudgeServlet judgeServlet;
    private ContestServlet contestServlet;
    private StudentServlet studentServlet;
    private StudentClassServlet studentClassServlet;

    @Autowired
    StudentController(QuestionServlet questionServlet, ContestServlet contestServlet,
                      JudgeServlet judgeServlet, StudentServlet studentServlet,
                      StudentClassServlet studentClassServlet){
        this.questionServlet = questionServlet;
        this.judgeServlet = judgeServlet;
        this.contestServlet = contestServlet;
        this.studentServlet = studentServlet;
        this.studentClassServlet = studentClassServlet;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value = "start", defaultValue = "1") int start,
                        @RequestParam(value = "size", defaultValue = "10") int size,
                        @RequestParam(value = "status", defaultValue = "0") int status){
        PageHelper.startPage(start,size,"questionID");
        List<Question> questionList = null;
        if(status == 0){
            questionList = questionServlet.selectQuestionByStatus(1);
        }else if (status == 2){
            questionList = questionServlet.selectQuestionByAC();
        }else if (status == 1){
            questionList = questionServlet.selectQuestionByNoAC();
        }

        PageInfo<Question> page = new PageInfo(questionList);
        model.addAttribute("page", page);
        model.addAttribute("status", status);

        return "student/index";
    }

    @RequestMapping(value = "/viewQuestion", method = RequestMethod.GET)
    public String ViewQuestion(Model model, @RequestParam(value = "questionID") String questionID){
        Question question = questionServlet.selectQuestionByID(questionID);
        model.addAttribute("question", question);

        return "student/viewQuestion";
    }

    @RequestMapping(value = "/viewSubmit", method = RequestMethod.GET)
    public String viewSubmit(Model model, @RequestParam(value = "submitID") String submitID){
        JudgeResult judgeResult = judgeServlet.selectJudgeBySubmitID(submitID);
        Question question = questionServlet.selectQuestionByNum(judgeResult.getQuestionNum());
        model.addAttribute("submit", judgeResult);
        model.addAttribute("question", question);

        return "student/ViewSubmit";
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
            judgeResultList = judgeServlet.selectJudgeByUserID(userMail);
        }else if(status == 2){
            judgeResultList = judgeServlet.selectACJudgeByUserID(userMail);
        }else if(status == 1){
            judgeResultList = judgeServlet.selectNoACJudgeByUserID(userMail);
        }
        PageInfo<JudgeResult> page = new PageInfo(judgeResultList);
        model.addAttribute("page", page);
        model.addAttribute("status", status);

        return "/student/submit";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(){
        return "/student/about";
    }

    @RequestMapping(value = "/contest")
    public String contest(Model model, @RequestParam(value = "start", defaultValue = "1") int start,
                          @RequestParam(value = "size", defaultValue = "10") int size,
                          @RequestParam(value = "status", defaultValue = "0") int status){

        PageHelper.startPage(start,size,"contestNum desc");
        List<Contest> contestList = null;
        if(status == 0){
            contestList = contestServlet.selectContest();
        }else if(status == 2){
            contestList = contestServlet.selectContest();
        }else if(status == 1){
            contestList = contestServlet.selectContest();
        }else if(status == 3){
            contestList = contestServlet.selectContest();
        }
        PageInfo<JudgeResult> page = new PageInfo(contestList);
        model.addAttribute("page", page);
        model.addAttribute("status", status);

        return "student/contest";
    }

    @RequestMapping(value = "/viewContest")
    public String viewContest(Model model, @RequestParam(value = "contestID") String contestID) throws ParseException {
        model.addAttribute("contest", contestServlet.selectContestByID(contestID));
        model.addAttribute("questionList", contestServlet.selectQuestionListByID(contestID));
        model.addAttribute("JudgeResult", contestServlet.selectJudgeResultByContest(contestID));
        model.addAttribute("contestUser", contestServlet.selectContestUser(contestID));
        model.addAttribute("rank", contestServlet.rank(contestID));

        return "student/viewContest";
    }

    @RequestMapping(value = "/user")
    public String auditUser(Model model, HttpServletRequest request){
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

        Student student = studentServlet.selectStudentByMail(userMail);
        StudentClass studentClass = studentClassServlet.selectStudentClassByID(student.getStudentClassID());
        model.addAttribute("student", student);
        model.addAttribute("studentClass", studentClass);

        return "student/user";
    }
}
