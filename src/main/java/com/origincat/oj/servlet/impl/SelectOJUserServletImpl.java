package com.origincat.oj.servlet.impl;

import com.origincat.oj.dao.OJUserDao;
import com.origincat.oj.dao.StudentDao;
import com.origincat.oj.enums.OJUserSignInEnum;
import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.pojo.Student;
import com.origincat.oj.servlet.SelectOJUserServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelectOJUserServletImpl implements SelectOJUserServlet {

    private OJUserDao ojUserDao;
    private StudentDao studentDao;

    @Autowired
    public SelectOJUserServletImpl(OJUserDao ojUserDao, StudentDao studentDao) {

        this.ojUserDao = ojUserDao;
        this.studentDao = studentDao;
    }

    @Override
    public OJUser selectOJUser(OJUser ojUser) {
        return ojUserDao.selectOJUser(ojUser.getUserMail());
    }

    @Override
    public boolean checkOJUser(OJUser ojUser) {
        return ojUserDao.selectOJUser(ojUser.getUserMail()) != null;
    }

    @Override
    public Student selectStudentID(Student student) {
        return studentDao.selectStudentByStudentID(student.getStudentID());
    }

    @Override
    public boolean checkStudentID(Student student) {
        return studentDao.selectStudentByStudentID(student.getStudentID()) != null;
    }

    @Override
    public OJUserSignInEnum checkOJUserSignIn(OJUser ojUser) {
        OJUser ojUser1 = ojUserDao.selectOJUser(ojUser.getUserMail());
        if(!ojUser.getUserPassWd().equals(ojUser1.getUserPassWd())){
            return OJUserSignInEnum.ERROR;
        }else{
            if(ojUser1.getUserStatus() == 2){
                return OJUserSignInEnum.CHECK;
            }else {
                if(ojUser1.getUserKind() == 1){
                    return OJUserSignInEnum.STUDENT;
                }else{
                    return OJUserSignInEnum.ADMIN;
                }
            }
        }
    }
}
