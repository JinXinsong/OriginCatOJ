package com.origincat.oj.servlet;

import com.origincat.oj.enums.OJUserSignInEnum;
import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.pojo.Student;

public interface SelectOJUserServlet {

    OJUser selectOJUser(OJUser ojUser);

    boolean checkOJUser(OJUser ojUser);

    Student selectStudentID(Student student);

    boolean checkStudentID(Student student);

    OJUserSignInEnum checkOJUserSignIn(OJUser ojUser);
}
