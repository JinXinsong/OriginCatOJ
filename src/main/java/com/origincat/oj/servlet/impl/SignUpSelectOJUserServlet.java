package com.origincat.oj.servlet.impl;

import com.origincat.oj.dao.OJUserDao;
import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.servlet.SelectOJUserServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpSelectOJUserServlet implements SelectOJUserServlet {

    private OJUserDao ojUserDao;

    @Autowired
    public SignUpSelectOJUserServlet(OJUserDao ojUserDao){
        this.ojUserDao = ojUserDao;
    }

    @Override
    public OJUser selectOJUser(OJUser ojUser){
        return ojUserDao.selectOJUser(ojUser.getUserMail());
    }

    @Override
    public boolean checkOJUser(OJUser ojUser){
        return ojUserDao.selectOJUser(ojUser.getUserMail()) != null;
    }
}
