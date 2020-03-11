package com.origincat.oj.dao;

import com.origincat.oj.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentDao {

    @Select("select * from Student where studentID = #{studentID}")
    public Student selectStudentByStudentID(String studentID);

    @Select("select * from Student where studentMail = #{studentMail}")
    public Student selectStudentByStudentMail(String studentMail);

    @Insert("insert into Student (studentMail, studentClassID, studentName, studentID) values (#{studentMail}, #{studentClassID}, #{studentName}, #{studentID})")
    public int insertStudentWithAll(Student student);
}
