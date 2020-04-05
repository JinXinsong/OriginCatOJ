package com.origincat.oj.servlet;

import com.origincat.oj.pojo.Question;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface QuestionServlet {

    boolean createQuestion(Question question, Map questionInput, Map questionOutput) throws IOException;

    List<Question> selectAllQuestion();

    List<Question> selectQuestionByStatus(int questionStatus);

    Question selectQuestionByID(String questionID);

    boolean editQuestion(Question question);

    boolean deleteQuestion(String questionID);
}
