package com.origincat.oj.servlet;

import com.origincat.oj.pojo.StudentClass;

import java.util.List;

public interface StudentClassServlet {

    List<StudentClass> selectAllStudentClass();

    StudentClass selectStudentClassByID(int classID);
}
