package com.origincat.oj.servlet;

import com.origincat.oj.pojo.JudgeResult;
import com.origincat.oj.pojo.JudgeTask;
import org.springframework.stereotype.Service;

@Service
public interface JudgeServlet {

    JudgeResult Judge(JudgeTask judgeTask);
}
