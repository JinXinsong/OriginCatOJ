package com.origincat.oj.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class OJUser {
    private int userID;

    private String userMail;

    private String userPassWd;

    private String userPhone;

    private Date createTime;

    private Date lastEditTime;

    private int userKind;

    private String userName;

    private int userStatus;
}
