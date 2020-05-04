package com.origincat.oj.servlet;

import com.origincat.oj.pojo.Contest;

import java.util.List;

public interface ContestServlet {

    List<Contest> selectContest();

    boolean createContest(Contest contest, String[] questionList);
}
