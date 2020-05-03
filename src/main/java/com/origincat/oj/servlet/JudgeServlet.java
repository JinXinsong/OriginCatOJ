package com.origincat.oj.servlet;

import com.origincat.oj.pojo.JudgeResult;
import com.origincat.oj.pojo.JudgeTask;
import com.origincat.oj.utils.Judge;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JudgeServlet {

    Boolean Judge(JudgeTask judgeTask);

    List<JudgeResult> selectJudgeByUserID(String userID);

    JudgeResult selectJudgeBySubmitID(String submitID);

    List<JudgeResult> selectACJudgeByUserID(String userID);

    List<JudgeResult> selectNoACJudgeByUserID(String userID);

    List<JudgeResult> selectJudge();

    List<JudgeResult> selectACJudge();

    List<JudgeResult> selectNoACJudge();
}
