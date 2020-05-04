package com.origincat.oj.servlet.impl;

import com.origincat.oj.dao.ContestDao;
import com.origincat.oj.pojo.Contest;
import com.origincat.oj.servlet.ContestServlet;
import com.origincat.oj.utils.CreateRandomID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestServletImpl implements ContestServlet {

    private ContestDao contestDao;

    @Autowired
    public ContestServletImpl(ContestDao contestDao){
        this.contestDao = contestDao;
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
            contestDao.AddContestQuestion(contestID, Integer.parseInt(questionList[i]));
        }
        return true;
    }
}
