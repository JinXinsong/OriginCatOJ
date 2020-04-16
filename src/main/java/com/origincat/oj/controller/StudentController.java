package com.origincat.oj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origincat.oj.pojo.Question;
import com.origincat.oj.servlet.QuestionServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    private QuestionServlet questionServlet;

    @Autowired
    StudentController(QuestionServlet questionServlet){
        this.questionServlet = questionServlet;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "status", defaultValue = "0") int status){
        PageHelper.startPage(start,size,"questionID");
        List<Question> questionList = questionServlet.selectQuestionByStatus(1);
        PageInfo<Question> page = new PageInfo(questionList);
        model.addAttribute("page", page);
        model.addAttribute("status", status);

        return "student/index";
    }

    @RequestMapping(value = "/viewQuestion", method = RequestMethod.GET)
    public String adminViewQuestion(Model model, @RequestParam(value = "questionID") String questionID){
        Question question = questionServlet.selectQuestionByID(questionID);
        model.addAttribute("question", question);

        return "student/viewQuestion";
    }
}
