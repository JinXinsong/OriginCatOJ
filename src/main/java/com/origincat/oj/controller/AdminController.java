package com.origincat.oj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origincat.oj.pojo.Question;
import com.origincat.oj.servlet.QuestionServlet;
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

    public AdminController(QuestionServlet questionServlet){
        this.questionServlet = questionServlet;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String adminIndex(Model model, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "10") int size){

        PageHelper.startPage(start,size,"questionID desc");
        List<Question> questionList = questionServlet.selectAllQuestion();
        PageInfo<Question> page = new PageInfo<>(questionList);
        model.addAttribute("page", page);

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
}
