package com.origincat.oj.servlet.impl;

import com.origincat.oj.dao.OJUserDao;
import com.origincat.oj.dao.StudentDao;
import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.pojo.Student;
import com.origincat.oj.servlet.SelectOJUserServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpSelectOJUserServlet implements SelectOJUserServlet {

    private OJUserDao ojUserDao;
    private StudentDao studentDao;

    @Autowired
    public SignUpSelectOJUserServlet(OJUserDao ojUserDao, StudentDao studentDao) {

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
}
