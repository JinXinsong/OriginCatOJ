package com.origincat.oj.servlet;

import com.origincat.oj.pojo.*;

import java.text.ParseException;
import java.util.List;

public interface ContestServlet {

    List<Contest> selectContest();

    boolean createContest(Contest contest, String[] questionList);

    Contest selectContestByID(String contestID);

    List<Question> selectQuestionListByID(String contestID);

    List<JudgeResult> selectJudgeResultByContest(String contestID);

    List<ContestUser> selectContestUser(String ContestID);

    boolean updateContestUser(String contestID, String userMail, int status);

    List<List<String>> rank(String contestID) throws ParseException;
}
