package com.origincat.oj.controller;

import com.origincat.oj.pojo.Contest;
import com.origincat.oj.pojo.Question;
import com.origincat.oj.servlet.ContestServlet;
import com.origincat.oj.servlet.OJUserServlet;
import com.origincat.oj.servlet.QuestionServlet;
import com.origincat.oj.utils.CreateRandomID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin", method = RequestMethod.POST)
public class AdminRestController {

    private QuestionServlet questionServlet;
    private OJUserServlet ojUserServlet;
    private ContestServlet contestServlet;

    @Autowired
    public AdminRestController(QuestionServlet questionServlet, OJUserServlet ojUserServlet,
                               ContestServlet contestServlet){
        this.questionServlet = questionServlet;
        this.ojUserServlet = ojUserServlet;
        this.contestServlet = contestServlet;
    }

    @ResponseBody
    @RequestMapping(value = "/createQuestion")
    public Map<String, Object> createQuestion(@RequestBody Map<String , Object> requestBody) throws IOException {
        Map<String, Object> ModelMap = new HashMap<>();

        String questionID = "Q"+CreateRandomID.getRandomID();
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

        ModelMap.put("success", questionServlet.createQuestion(question, questionInput, questionOutput));

        return ModelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/editQuestion")
    public Map<String, Object> editQuestion(@RequestBody Map<String , Object> requestBody) throws IOException {
        Map<String, Object> ModelMap = new HashMap<>();

        String questionID = requestBody.get("questionID").toString();
        String questionTitle = requestBody.get("questionTitle").toString();
        String questionInputSimple = requestBody.get("questionInputSimple").toString();
        String questionOutputSimple = requestBody.get("questionOutputSimple").toString();
        String questionContent = requestBody.get("question").toString();
        String questionPrompt = requestBody.get("questionPrompt").toString();
        int questionStatus = Integer.parseInt(requestBody.get("questionStatus").toString());
        int questionTimeLimit = Integer.parseInt(requestBody.get("questionTimeLimit").toString());
        int questionMemoryLimit = Integer.parseInt(requestBody.get("questionMemoryLimit").toString());

        Question question = new Question();
        question.setQuestionID(questionID);
        question.setQuestionTitle(questionTitle);
        question.setQuestionStatus(questionStatus);
        question.setQuestionTimeLimit(questionTimeLimit);
        question.setQuestionMemoryLimit(questionMemoryLimit);
        question.setQuestionContent(questionContent);
        question.setQuestionInputSimple(questionInputSimple);
        question.setQuestionOutputSimple(questionOutputSimple);
        question.setQuestionPrompt(questionPrompt);

        ModelMap.put("success", questionServlet.editQuestion(question));

        return ModelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteQuestion")
    public Map<String, Object> deleteQuestion(@RequestParam(value = "questionID") String questionID){
        Map<String, Object> ModelMap = new HashMap<>();

        ModelMap.put("success", questionServlet.deleteQuestion(questionID));

        return ModelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/auditUser")
    public Map<String, Object> auditUser(@RequestParam(value = "userMail") String userMail, @RequestParam(value = "status") int status){
        Map<String, Object> ModelMap = new HashMap<>();

        ModelMap.put("success", ojUserServlet.updateOJUserStatus(status, userMail));

        return ModelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/createContest")
    public Map<String, Object> createContest(@RequestBody Map<String , Object> requestBody){
        Map<String, Object> modelMap = new HashMap<>();

        String contestName = requestBody.get("contestName").toString();
        String startTime = requestBody.get("startTime").toString();
        String endTime = requestBody.get("endTime").toString();
        String contestInf = requestBody.get("contestInf").toString();
        String[] questionList = requestBody.get("questionList").toString().replace("]", "").replace("[", "").replace(" ", "").split(",");

        Contest contest = new Contest();
        contest.setContestInf(contestInf);
        contest.setContestName(contestName);
        contest.setStartTime(startTime);
        contest.setEndTime(endTime);

        if(contestServlet.createContest(contest, questionList)){
            modelMap.put("success", true);
        }else {
            modelMap.put("success", false);
            modelMap.put("msg", "信息错误");
        }

        return modelMap;
    }
}
