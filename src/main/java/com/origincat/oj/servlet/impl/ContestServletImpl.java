package com.origincat.oj.servlet.impl;

import com.origincat.oj.dao.ContestDao;
import com.origincat.oj.dao.OJUserDao;
import com.origincat.oj.dao.QuestionDao;
import com.origincat.oj.pojo.*;
import com.origincat.oj.servlet.ContestServlet;
import com.origincat.oj.utils.CreateRandomID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        List<ContestQuestion> contestQuestionList = contestDao.selectContestQuestionListByID(contestID);
        for(ContestQuestion contestQuestion:contestQuestionList){
            if(contestDao.selectContestQuestionUser(contestQuestion.getContestQuestionID(), userMail) == null){
                contestDao.addContestQuestionUser(contestQuestion.getContestQuestionID(), userMail);
            }
        }
        return contestDao.updateContestUserStatus(contestID, userMail, status) > 0;
    }

    @Override
    public List<List<String>> rank(String contestID) throws ParseException {
        SimpleDateFormat fmt =new SimpleDateFormat("yyyyMMddHHmm");
        long startTime = fmt.parse(contestDao.selectContestByID(contestID).getStartTime()).getTime();
        List<List<String>> listList = new LinkedList<>();
        List<ContestUser> contestUserList = contestDao.selectContestUser(contestID);
        List<ContestQuestion> contestQuestionList = contestDao.selectContestQuestionListByID(contestID);
        for(ContestUser contestUser:contestUserList){
            if(contestUser.getStatus() == 1) {
                List<String> stringList = new LinkedList<>();
                int num = 0;
                for (ContestQuestion contestQuestion : contestQuestionList) {
                    ContestQuestionUser contestQuestionUser = contestDao.selectContestQuestionUser(contestQuestion.getContestQuestionID(), contestUser.getUserMail());
                    stringList.add(contestUser.getUserName());
                    if (contestQuestionUser.getAcceptNum() > 0) {
                        long date = fmt.parse(contestQuestionUser.getACTime()).getTime();
                        num = num + (int) ((date - startTime) / (1000 * 60));
                    }
                    stringList.add("(" + contestQuestionUser.getAcceptNum() + "/" + contestQuestionUser.getInputNum() + ")");
                }
                stringList.add("" + num);
                listList.add(stringList);
            }
        }

        return listList;
    }
}
