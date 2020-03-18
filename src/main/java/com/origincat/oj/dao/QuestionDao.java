package com.origincat.oj.dao;

import com.origincat.oj.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionDao {

    @Insert("insert into Question (questionID, questionTitle, questionInput, questionAccept, questionStatus, questionTimeLimit, questionMemoryLimit, questionContent, questionInputSimple, questionOutputSimple, questionPrompt) values (#{questionID}, #{questionTitle}, #{questionInput}, #{questionAccept}, #{questionStatus}, #{questionTimeLimit}, #{questionMemoryLimit}, #{questionContent}, #{questionInputSimple}, #{questionOutputSimple}, #{questionPrompt})")
    public int createQuestion(Question question);

    @Select("select * from Question")
    public List<Question> selectAllQuestion();

}
