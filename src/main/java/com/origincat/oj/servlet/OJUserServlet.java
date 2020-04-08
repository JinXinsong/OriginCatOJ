package com.origincat.oj.servlet;

import com.origincat.oj.enums.OJUserSignUpEnum;
import com.origincat.oj.pojo.OJUser;

import java.util.List;

public interface OJUserServlet {
    OJUserSignUpEnum signUp(OJUser ojUser);

    List<OJUser> selectAllOJUser();

    boolean updateOJUserStatus(int status, String userMail);
}
