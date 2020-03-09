package com.origincat.oj.servlet;

import com.origincat.oj.pojo.OJUser;

public interface SelectOJUserServlet {

    OJUser selectOJUser(OJUser ojUser);

    boolean checkOJUser(OJUser ojUser);
}
