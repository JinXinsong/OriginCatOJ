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

    @Select("select * from JudgeResult where status != 0 and userMail = #{userMail}")
    List<JudgeResult> selectNoACJudgeByID(String userMail);

    @Select("select * from JudgeResult where userMail = #{userMail}")
    List<JudgeResult> selectJudgeByID(String userMail);

    @Select("select * from JudgeResult where submitID = #{submitID}")
    JudgeResult selectJudgeBySubmitID(String submitID);

    @Insert("insert into JudgeResult (userMail, submitID, questionNum, status, timeUsed, memoryUsed, errorMessage, time, questionTitle) values (#{userMail}, #{submitID}, #{questionNum}, #{status}, #{timeUsed}, #{memoryUsed}, #{errorMessage}, #{time}, #{questionTitle})")
    int insertJudge(JudgeResult judgeResult);

    @Update("update JudgeResult set status = #{status}, timeUsed = #{timeUsed}, memoryUsed = #{memoryUsed}, errorMessage = #{errorMessage} where submitID = #{submitID}")
    int updateJudge(JudgeResult judgeResult);

    @Select("select * from JudgeResult")
    List<JudgeResult> selectJudge();

    @Select("select * from JudgeResult where status = 0")
    List<JudgeResult> selectACJudge();

    @Select("select * from JudgeResult where status != 0")
    List<JudgeResult> selectNoACJudge();
}
