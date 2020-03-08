package com.origincat.oj.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class OJUser {
    private int userID;

    private String userMail;

    private String userPasswd;

    private String userPhone;

    private Date createTime;

    private Date lastEditTime;

    private int userKind;

    private String userName;
}
