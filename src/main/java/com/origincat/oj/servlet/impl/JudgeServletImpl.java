package com.origincat.oj.servlet.impl;

import com.origincat.oj.dao.JudgeDao;
import com.origincat.oj.pojo.JudgeResult;
import com.origincat.oj.pojo.JudgeTask;
import com.origincat.oj.servlet.JudgeServlet;
import com.origincat.oj.utils.CreateRandomID;
import com.origincat.oj.utils.Judge;
import com.origincat.oj.utils.TimeString;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeServletImpl implements JudgeServlet {

    private JudgeDao judgeDao;

    public JudgeServletImpl(JudgeDao judgeDao){
        this.judgeDao = judgeDao;
    }

    @Override
    public Boolean Judge(JudgeTask judgeTask) {
        String submitID = CreateRandomID.getRandomID();
        judgeTask.setSubmitID(submitID);
        JudgeResult judgeResult = new JudgeResult();
        judgeResult.setSubmitID(submitID);
        judgeResult.setQuestionNum(judgeTask.getQuestionNum());
        judgeResult.setUserMail(judgeTask.getUserMail());
        judgeResult.setStatus(999);
        judgeResult.setTime(TimeString.getTime());
        judgeDao.insertJudge(judgeResult);
        JudgeResult judgeResult1 = Judge.judge(judgeTask);
        judgeDao.updateJudge(judgeResult1);

        return true;
    }

    @Override
    public List<JudgeResult> selectJudgeByUserID(String userMail) {
        return judgeDao.selectJudgeByID(userMail);
    }
}
