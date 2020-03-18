package com.origincat.oj.controller;

import com.alibaba.fastjson.JSON;
import com.origincat.oj.pojo.Question;
import com.origincat.oj.servlet.QuestionServlet;
import com.origincat.oj.utils.CreateRandomID;
import com.origincat.oj.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/admin")
public class AdminRestController {

    private QuestionServlet questionServlet;

    @Autowired
    public AdminRestController(QuestionServlet questionServlet){
        this.questionServlet = questionServlet;
    }

    @ResponseBody
    @RequestMapping(value = "/createQuestion", method = RequestMethod.POST)
    public Map<String, Object> createQuestion(@RequestBody Map<String , Object> requestBody) throws IOException {
        Map<String, Object> ModelMap = new HashMap<>();

        String questionID = CreateRandomID.getRandomID();
        String questionTitle = requestBody.get("questionTitle").toString();
        String questionInputSimple = requestBody.get("questionInputSimple").toString();
        String questionOutputSimple = requestBody.get("questionOutputSimple").toString();
        String questionContent = requestBody.get("question").toString();
        String questionPrompt = requestBody.get("questionPrompt").toString();
        int questionStatus = Integer.parseInt(requestBody.get("questionStatus").toString());
        int questionTimeLimit = Integer.parseInt(requestBody.get("questionTimeLimit").toString());
        int questionMemoryLimit = Integer.parseInt(requestBody.get("questionMemoryLimit").toString());

        Map questionInput = (Map) requestBody.get("questionInput");
        Map questionOutput = (Map) requestBody.get("questionOutput");

        Question question = new Question();
        question.setQuestionID(questionID);
        question.setQuestionTitle(questionTitle);
        question.setQuestionAccept(0);
        question.setQuestionInput(0);
        question.setQuestionStatus(questionStatus);
        question.setQuestionTimeLimit(questionTimeLimit);
        question.setQuestionMemoryLimit(questionMemoryLimit);
        question.setQuestionContent(questionContent);
        question.setQuestionInputSimple(questionInputSimple);
        question.setQuestionOutputSimple(questionOutputSimple);
        question.setQuestionPrompt(questionPrompt);

        if(questionServlet.createQuestion(question, questionInput, questionOutput)){
            ModelMap.put("success", true);
        }else {
            ModelMap.put("success", false);
        }

        return ModelMap;
    }
}
