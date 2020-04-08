package com.origincat.oj.servlet.impl;

import com.origincat.oj.dao.OJUserDao;
import com.origincat.oj.enums.OJUserSignUpEnum;
import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.servlet.OJUserServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OJUserServletImpl implements OJUserServlet {

    private OJUserDao ojUserDao;

    @Autowired
    OJUserServletImpl(OJUserDao ojUserDao){
        this.ojUserDao = ojUserDao;
    }

    @Transactional
    @Override
    public OJUserSignUpEnum signUp(OJUser ojUser) {
        if(ojUser == null){
            return OJUserSignUpEnum.ERROR;
        }
        try{
            int effectedNum = ojUserDao.signUp(ojUser);
            if (effectedNum <=0 ){
                throw new RuntimeException("注册失败");
            }
        }catch (Exception e){
            throw new RuntimeException("loginError:" + e.getMessage());
        }
        return OJUserSignUpEnum.CHECK;
    }

    @Override
    public List<OJUser> selectAllOJUser() {
        return ojUserDao.selectAllOJUser();
    }

    @Override
    public boolean updateOJUserStatus(int status, String userMail) {
        return ojUserDao.updateOJUserStatus(status, userMail)>0;
    }
}
