package com.origincat.oj.servlet;

import com.origincat.oj.pojo.JudgeResult;
import com.origincat.oj.pojo.JudgeTask;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JudgeServlet {

    Boolean Judge(JudgeTask judgeTask);

    List<JudgeResult> selectJudgeByUserID(String userID);
}
