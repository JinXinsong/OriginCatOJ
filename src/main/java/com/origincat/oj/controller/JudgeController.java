package com.origincat.oj.controller;

import com.alibaba.fastjson.JSON;

import com.origincat.oj.pojo.JudgeResult;
import com.origincat.oj.pojo.JudgeTask;
import com.origincat.oj.servlet.JudgeServlet;
import com.origincat.oj.utils.Judge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JudgeController {

    private JudgeServlet judgeServlet;

    @Autowired
    public JudgeController(JudgeServlet judgeServlet){
        this.judgeServlet = judgeServlet;
    }

    @RequestMapping(value = "/judge", method = RequestMethod.POST)
    public String judge(@RequestBody Map<String, Object> request){
        JudgeTask judgeTask = JSON.parseObject(JSON.toJSONString(request), JudgeTask.class);
        JudgeResult judgeResult = judgeServlet.Judge(judgeTask);
        return JSON.toJSONString(judgeResult);
    }
}