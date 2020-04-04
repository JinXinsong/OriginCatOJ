package com.origincat.oj.servlet;

import com.origincat.oj.pojo.Question;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface QuestionServlet {

    boolean createQuestion(Question question, Map questionInput, Map questionOutput) throws IOException;

    List<Question> selectAllQuestion();

    Question selectQuestionByID(String questionID);

    boolean editQuestion(Question question);
}
