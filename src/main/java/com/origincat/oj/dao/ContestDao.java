package com.origincat.oj.dao;

import com.origincat.oj.pojo.Contest;
import com.origincat.oj.pojo.ContestQuestionUser;
import com.origincat.oj.pojo.ContestUser;
import com.origincat.oj.pojo.JudgeResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContestDao {
    @Select("select * from Contest")
    List<Contest> selectContest();

    @Insert("insert into Contest (contestID, contestName, startTime, endTime, contestInf) values (#{contestID}, #{contestName}, #{startTime}, #{endTime}, #{contestInf})")
    int createContest(Contest contest);

    @Insert("insert into ContestQuestion (contestID, questionNum, inputNum, AcceptNum, contestQuestionNum) values (#{contestID}, #{questionNum}, 0, 0, #{contestQuestionNum})")
    int AddContestQuestion(String contestID, int questionNum, int contestQuestionNum);

    @Select("select * from Contest where contestID = #{contestID}")
    Contest selectContestByID(String contestID);

    @Select("select questionNum from ContestQuestion where contestID = #{contestID}")
    List<Integer> selectQuestionListByID(String contestID);

    @Insert("insert into ContestSubmit (contestID, submitID) values (#{contestID}, #{submitID})")
    int AddContestSubmit(String contestID, String submitID);

    @Select("select jr.* from JudgeResult as jr, ContestSubmit as cs where cs.contestID = #{contestID} and cs.submitID = jr.submitID")
    List<JudgeResult> selectJudgeResultByContest(String contestID);

    @Insert("insert into ContestQuestionUser (contestQuestionID, userMail, inputNum, acceptNum, waNum) values (#{contestQuestionID}, #{userMail}, -1, 0, 0)")
    int addContestQuestionUser(int contestQuestionID, String userMail);

    @Update("update ContestQuestionUser set inputNum = inputNum+1, acceptNum = acceptNum+1, ACTime = #{ACTime} where contestQuestionID = #{contestQuestionID} and userMail = #{userMail}")
    ContestQuestionUser updateACContestQuestionUser(int contestQuestionID, String userMail, String ACTime);

    @Update("update ContestQuestionUser set inputNum = inputNum+1, waNum = waNum+1 where contestQuestionID = #{contestQuestionID} and userMail = #{userMail}")
    ContestQuestionUser updateWAContestQuestionUser(int contestQuestionID, String userMail);

    @Select("select * form ContestQuestionUser where contestQuestionID = #{contestQuestionID} and userMail = #{userMail}")
    ContestQuestionUser selectContestQuestionUser(int contestQuestionID, String userMail);

    @Insert("insert into ContestUser (contestID, userMail, userName, status) values (#{contestID}, #{userMail}, #{userName}, #{status})")
    int addContestUser(ContestUser contestUser);

    @Update("update ContestUser set status = #{status} where contestID = #{contestID} and userMail = #{userMail}")
    int updateContestUserStatus(String contestID, String userMail, int status);

    @Select("select * from ContestUser where contestID = #{contestID}")
    List<ContestUser> selectContestUser(String contestID);
}
