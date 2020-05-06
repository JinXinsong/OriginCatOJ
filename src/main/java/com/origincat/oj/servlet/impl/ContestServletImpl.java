package com.origincat.oj.servlet.impl;

import com.origincat.oj.dao.ContestDao;
import com.origincat.oj.dao.OJUserDao;
import com.origincat.oj.dao.QuestionDao;
import com.origincat.oj.pojo.*;
import com.origincat.oj.servlet.ContestServlet;
import com.origincat.oj.utils.CreateRandomID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContestServletImpl implements ContestServlet {

    private ContestDao contestDao;
    private QuestionDao questionDao;
    private OJUserDao ojUserDao;

    @Autowired
    public ContestServletImpl(ContestDao contestDao, QuestionDao questionDao, OJUserDao ojUserDao){
        this.ojUserDao = ojUserDao;
        this.contestDao = contestDao;
        this.questionDao = questionDao;
    }

    @Override
    public List<Contest> selectContest() {
        return contestDao.selectContest();
    }

    @Override
    public boolean createContest(Contest contest, String[] questionList) {
        String contestID = CreateRandomID.getRandomID();
        contest.setContestID(contestID);
        contestDao.createContest(contest);
        for(int i=0; i<questionList.length; i++){
            contestDao.AddContestQuestion(contestID, Integer.parseInt(questionList[i]), i+1);
        }
        return true;
    }

    @Override
    public Contest selectContestByID(String contestID) {
        return contestDao.selectContestByID(contestID);
    }

    @Override
    public List<Question> selectQuestionListByID(String contestID) {
        List<Integer> integers = contestDao.selectQuestionListByID(contestID);
        List<Question> questionList = new LinkedList<>();
        for(int i=0; i<integers.size(); i++){
            questionList.add(questionDao.selectQuestionByNum(integers.get(i)));
        }
        return questionList;
    }

    @Override
    public List<JudgeResult> selectJudgeResultByContest(String contestID) {
        return contestDao.selectJudgeResultByContest(contestID);
    }

    @Override
    public List<ContestUser> selectContestUser(String contestID) {

        return contestDao.selectContestUser(contestID);
    }

    @Override
    public boolean updateContestUser(String contestID, String userMail, int status) {
        return contestDao.updateContestUserStatus(contestID, userMail, status) > 0;
    }
}
