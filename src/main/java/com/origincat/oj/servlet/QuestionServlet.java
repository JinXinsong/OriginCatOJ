package com.origincat.oj.servlet;

import com.origincat.oj.pojo.Question;

import java.io.IOException;
import java.util.Map;

public interface QuestionServlet {

    public boolean createQuestion(Question question, Map questionInput, Map questionOutput) throws IOException;
}
