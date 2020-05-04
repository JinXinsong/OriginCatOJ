package com.origincat.oj.dao;

import com.origincat.oj.pojo.Contest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContestDao {
    @Select("select * from Contest")
    List<Contest> selectContest();

    @Insert("insert into (Contest contestID, contestName, startTime, endTime, contestInf) values (#{contestID}, #{contestName}, #{startTime}, #{endTime}, #{contestInf})")
    int createContest(Contest contest);

    @Insert("insert into (contestID, questionNum, inputNum, AcceptNum) values (#{contestID}, #{questionID}, 0, 0)")
    int AddContestQuestion(String contestID, int questionID);
}
