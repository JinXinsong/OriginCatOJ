package com.origincat.oj.servlet;

import com.origincat.oj.enums.OJUserSignUpEnum;
import com.origincat.oj.pojo.OJUser;

public interface OJUserServlet {
    OJUserSignUpEnum signUp(OJUser ojUser);
}
