package com.origincat.oj.dao;

import com.origincat.oj.pojo.OJUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OJUserDao {

    @Insert("insert into OJUser (userMail, userPassWd, userPhone, createTime, lastEditTime, userKind, userName, userStatus) values (#{userMail}, #{userPassWd}, #{userPhone}, #{createTime}, #{lastEditTime}, #{userKind}, #{userName}, #{userStatus})")
    public int signUp(OJUser ojUser);

    @Select("select * from OJUser where userMail = #{userMail}")
    public OJUser selectOJUser(String userMail);

}