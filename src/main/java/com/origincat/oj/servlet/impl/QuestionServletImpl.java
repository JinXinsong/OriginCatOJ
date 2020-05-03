package com.origincat.oj.servlet.impl;

import com.origincat.oj.dao.QuestionDao;
import com.origincat.oj.pojo.Question;
import com.origincat.oj.servlet.QuestionServlet;
import com.origincat.oj.utils.PropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServletImpl implements QuestionServlet {

    private QuestionDao questionDao;

    public QuestionServletImpl(QuestionDao questionDao){
        this.questionDao = questionDao;
    }

    @Transactional
    @Override
    public boolean createQuestion(Question question, Map questionInput, Map questionOutput) throws IOException {

        String path = PropertiesUtil.StringValue("judge_data") + "/" + question.getQuestionID();
        File file = new File(path);
        file.mkdirs();

        for(Object object : questionInput.keySet()){
            File file1 = new File(path + "/" + object.toString() + ".in");
            file1.createNewFile();
            OutputStream outputStream = new FileOutputStream(file1);
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.print(questionInput.get(object).toString()+"\n");
            printWriter.close();
            outputStream.close();
        }

        for(Object object : questionOutput.keySet()) {
            File file1 = new File(path + "/" + object.toString() + ".out");
            file1.createNewFile();
            OutputStream outputStream = new FileOutputStream(file1);
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.print(questionOutput.get(object).toString());
            printWriter.close();
            outputStream.close();
        }

        return questionDao.createQuestion(question) > 0;
    }

    @Override
    public List<Question> selectAllQuestion() {
        return questionDao.selectAllQuestion();
    }

    @Override
    public List<Question> selectQuestionByAC() {
        return questionDao.selectQuestionByAC();
    }

    @Override
    public List<Question> selectQuestionByNoAC() {
        List<Question> AC = questionDao.selectQuestionByAC();
        List<Question> NoAC = questionDao.selectQuestionByNoAC();
        List<Question> result = questionDao.selectQuestionByNoAC();
        result.clear();
        for(int i=0; i<NoAC.size(); i++){
            boolean addSign = true;
            for(int j=0; j<AC.size(); j++){
                if(NoAC.get(i).getQuestionNum() == AC.get(j).getQuestionNum()){
                    addSign = false;
                    break;
                }
            }
            if(addSign){
                result.add(NoAC.get(i));
            }
        }
        return result;
    }

    @Override
    public List<Question> selectQuestionByStatus(int questionStatus) {
        return questionDao.selectQuestionByStatus(questionStatus);
    }

    @Override
    public Question selectQuestionByID(String questionID) {
        return questionDao.selectQuestionByID(questionID);
    }

    @Override
    public Question selectQuestionByNum(int questionNum) {
        return questionDao.selectQuestionByNum(questionNum);
    }

    @Override
    public boolean editQuestion(Question question) {
        return questionDao.editQuestion(question) > 0;
    }

    @Override
    public boolean deleteQuestion(String questionID) {
        return questionDao.deleteQuestion(questionID) > 0;
    }
}
