package com.origincat.oj.servlet;

import com.origincat.oj.enums.StudentSignUpEnum;
import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.pojo.Student;

public interface StudentServlet {

    public StudentSignUpEnum insertStudent(Student student, OJUser ojUser);

    Student selectStudentByMail(String userMail);
}