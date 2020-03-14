package com.origincat.oj.servlet.impl;

import com.origincat.oj.dao.OJUserDao;
import com.origincat.oj.dao.StudentDao;
import com.origincat.oj.enums.StudentSignUpEnum;
import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.pojo.Student;
import com.origincat.oj.servlet.StudentServlet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class StudentServletImpl implements StudentServlet {

    private StudentDao studentDao;
    private OJUserDao ojUserDao;

    public StudentServletImpl(StudentDao studentDao, OJUserDao ojUserDao){
        this.studentDao = studentDao;
        this.ojUserDao = ojUserDao;
    }

    @Transactional
    @Override
    public StudentSignUpEnum insertStudent(Student student, OJUser ojUser) {
        studentDao.insertStudentWithAll(student);
        Date createTime = new Date();
        ojUser.setCreateTime(createTime);
        ojUser.setLastEditTime(createTime);
        ojUser.setUserKind(1);
        ojUser.setUserStatus(2);
        ojUser.setUserName(student.getStudentName());
        ojUserDao.signUp(ojUser);

        return StudentSignUpEnum.stateOf(2);
    }
}
