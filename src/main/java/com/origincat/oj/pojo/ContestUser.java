package com.origincat.oj.pojo;

import lombok.Data;

@Data
public class ContestUser {
    String contestID;

    String userMail;

    String userName;

    int status;

    int contestUserID;
}
