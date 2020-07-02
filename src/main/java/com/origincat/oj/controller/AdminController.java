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
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping(value = "/admin", method = RequestMethod.GET)
public class AdminController {

    private QuestionServlet questionServlet;
    private OJUserServlet ojUserServlet;
    private StudentServlet studentServlet;
    private StudentClassServlet studentClassServlet;
    private JudgeServlet judgeServlet;
    private ContestServlet contestServlet;

    public AdminController(QuestionServlet questionServlet, OJUserServlet ojUserServlet,
                           StudentServlet studentServlet, StudentClassServlet studentClassServlet,
                           JudgeServlet judgeServlet, ContestServlet contestServlet){

        this.questionServlet = questionServlet;
        this.ojUserServlet = ojUserServlet;
        this.studentServlet = studentServlet;
        this.studentClassServlet = studentClassServlet;
        this.judgeServlet = judgeServlet;
        this.contestServlet = contestServlet;
    }

    @RequestMapping(value = "/index")
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

    @RequestMapping(value = "/createQuestion")
    public String adminCreateQuestion(){
        return "admin/createQuestion";
    }

    @RequestMapping(value = "/editQuestion", method = RequestMethod.GET)
    public String adminEditQuestion(Model model, @RequestParam(value = "questionID") String questionID){
        Question question = questionServlet.selectQuestionByID(questionID);
        model.addAttribute("question", question);

        return "admin/editQuestion";
    }

    @RequestMapping(value = "/viewQuestion")
    public String adminViewQuestion(Model model, @RequestParam(value = "questionID") String questionID){
        Question question = questionServlet.selectQuestionByID(questionID);
        model.addAttribute("question", question);

        return "admin/viewQuestion";
    }

    @RequestMapping(value = "/user")
    public String user(Model model, @RequestParam(value = "start", defaultValue = "1") int start,
                       @RequestParam(value = "size", defaultValue = "10") int size){

        PageHelper.startPage(start,size,"userID desc");
        List<OJUser> ojUserList = ojUserServlet.selectAllOJUser();
        PageInfo<Question> page = new PageInfo(ojUserList);
        model.addAttribute("page", page);

        return "admin/user";
    }

    @RequestMapping(value = "/auditUser")
    public String auditUser(Model model, @RequestParam(value = "userMail") String userMail){

        Student student = studentServlet.selectStudentByMail(userMail);
        StudentClass studentClass = studentClassServlet.selectStudentClassByID(student.getStudentClassID());
        model.addAttribute("student", student);
        model.addAttribute("studentClass", studentClass);

        return "admin/auditUser";
    }

    @RequestMapping(value = "/submit")
    public String submit(Model model, @RequestParam(value = "start", defaultValue = "1") int start,
                         @RequestParam(value = "size", defaultValue = "10") int size, 
                         @RequestParam(value = "status", defaultValue = "0") int status){
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

    @RequestMapping(value = "/viewSubmit")
    public String viewSubmit(Model model, @RequestParam(value = "submitID") String submitID){
        JudgeResult judgeResult = judgeServlet.selectJudgeBySubmitID(submitID);
        Question question = questionServlet.selectQuestionByNum(judgeResult.getQuestionNum());
        model.addAttribute("submit", judgeResult);
        model.addAttribute("question", question);

        return "admin/viewSubmit";
    }

    @RequestMapping(value = "/about")
    public String about(Model model){
        return "admin/about";
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

        return "admin/contest";
    }

    @RequestMapping(value = "/createContest")
    public String createContest(Model model){
        return "admin/createContest";
    }

    @RequestMapping(value = "/viewContest")
    public String viewContest(Model model, @RequestParam(value = "contestID") String contestID) throws ParseException {
        model.addAttribute("contest", contestServlet.selectContestByID(contestID));
        model.addAttribute("questionList", contestServlet.selectQuestionListByID(contestID));
        model.addAttribute("JudgeResult", contestServlet.selectJudgeResultByContest(contestID));
        model.addAttribute("contestUser", contestServlet.selectContestUser(contestID));
        model.addAttribute("rank", contestServlet.rank(contestID));

        return "admin/viewContest";
    }
}
