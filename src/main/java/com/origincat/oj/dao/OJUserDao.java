package com.origincat.oj.dao;

import com.origincat.oj.pojo.OJUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OJUserDao {

    @Insert("insert into OJUser (userMail, userPassWd, userPhone, createTime, lastEditTime, userKind, userName, userStatus) values (#{userMail}, #{userPassWd}, #{userPhone}, #{createTime}, #{lastEditTime}, #{userKind}, #{userName}, #{userStatus})")
    int signUp(OJUser ojUser);

    @Select("select * from OJUser where userMail = #{userMail}")
    OJUser selectOJUser(String userMail);

    @Select("select * from OJUser")
    List<OJUser> selectAllOJUser();

    @Update("update OJUser set userStatus = #{userStatus} where userMail = #{userMail}")
    int updateOJUserStatus(int userStatus, String userMail);
}