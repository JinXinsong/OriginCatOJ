package com.origincat.oj.servlet.impl;

import com.origincat.oj.dao.StudentClassDao;
import com.origincat.oj.pojo.StudentClass;
import com.origincat.oj.servlet.StudentClassServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentClassServletImpl implements StudentClassServlet {

    private StudentClassDao studentClassDao;

    @Autowired
    public StudentClassServletImpl(StudentClassDao studentClassDao){
        this.studentClassDao = studentClassDao;
    }

    @Override
    public List<StudentClass> selectAllStudentClass() {
        return studentClassDao.selectAllStudentClass();
    }
}
