package com.origincat.oj.dao;

import com.origincat.oj.pojo.JudgeResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface JudgeDao {

    @Select("select * from JudgeResult where status = 0 and userMail = #{userMail}")
    List<JudgeResult> selectACJudgeByID(String userMail);

    @Select("select * from JudgeResult where userMail = #{userMail}")
    List<JudgeResult> selectJudgeByID(String userMail);

    @Select("select count(*) as nums from judgeResult where userMail = #{userMail}")
    int selectJudgeByIDNum(String userMail);

    @Insert("insert into JudgeResult (userMail, submitID, questionNum, status, timeUsed, memoryUsed, errorMessage, time) values (#{userMail}, #{submitID}, #{questionNum}, #{status}, #{timeUsed}, #{memoryUsed}, #{errorMessage}, #{time})")
    int insertJudge(JudgeResult judgeResult);

    @Update("update JudgeResult set status = #{status}, timeUsed = #{timeUsed}, memoryUsed = #{memoryUsed}, errorMessage = #{errorMessage} where submitID = #{submitID}")
    int updateJudge(JudgeResult judgeResult);
}
