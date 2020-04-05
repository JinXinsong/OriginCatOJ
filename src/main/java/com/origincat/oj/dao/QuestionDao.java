package com.origincat.oj.dao;

import com.origincat.oj.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionDao {

    @Insert("insert into Question (questionID, questionTitle, questionInput, questionAccept, questionStatus, questionTimeLimit, questionMemoryLimit, questionContent, questionInputSimple, questionOutputSimple, questionPrompt) values (#{questionID}, #{questionTitle}, #{questionInput}, #{questionAccept}, #{questionStatus}, #{questionTimeLimit}, #{questionMemoryLimit}, #{questionContent}, #{questionInputSimple}, #{questionOutputSimple}, #{questionPrompt})")
    int createQuestion(Question question);

    @Select("select * from Question where questionStatus != 3")
    List<Question> selectAllQuestion();

    @Select("select * from Question where questionStatus = #{questionStatus}")
    List<Question> selectQuestionByStatus(int questionStatus);

    @Select("select * from Question where questionID = #{questionID}")
    Question selectQuestionByID(String questionID);

    @Update("update Question set questionTitle = #{questionTitle}, questionStatus = #{questionStatus}, questionTimeLimit = #{questionTimeLimit}, questionMemoryLimit = #{questionMemoryLimit}, questionContent = #{questionContent}, questionInputSimple = #{questionInputSimple}, questionOutputSimple = #{questionOutputSimple}, questionPrompt = #{questionPrompt} where questionID = #{questionID}")
    int editQuestion(Question question);

    @Update("update Question set questionStatus = 3 where questionID = #{questionID}")
    int deleteQuestion(String questionID);
}
