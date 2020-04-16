package com.origincat.oj.servlet.impl;

import com.origincat.oj.pojo.JudgeResult;
import com.origincat.oj.pojo.JudgeTask;
import com.origincat.oj.servlet.JudgeServlet;
import com.origincat.oj.utils.Judge;

public class JudgeServletImpl implements JudgeServlet {

    @Override
    public JudgeResult Judge(JudgeTask judgeTask) {
        return Judge.judge(judgeTask);
    }
}
