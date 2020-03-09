package com.origincat.oj;

import com.origincat.oj.dao.OJUserDao;
import com.origincat.oj.pojo.OJUser;
import com.origincat.oj.servlet.SelectOJUserServlet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SignUpTest {
    private OJUserDao ojUserDao;
    private SelectOJUserServlet selectOJUserServlet;

    @Autowired
    public SignUpTest(OJUserDao ojUserDao, SelectOJUserServlet selectOJUserServlet){
        this.ojUserDao = ojUserDao;
        this.selectOJUserServlet = selectOJUserServlet;
    }

    @Test
    public void selectUser(){
        OJUser ojUser = new OJUser();
        ojUser.setUserMail("jinxinsong@outlook.com");
        OJUser ojUser1 = ojUserDao.selectOJUser(ojUser.getUserMail());
        boolean key = selectOJUserServlet.checkOJUser(ojUser);
        assertEquals(ojUser1.getUserID(), 1);
        assertEquals(key, true);
    }
}
