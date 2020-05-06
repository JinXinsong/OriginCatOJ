package com.origincat.oj.pojo;

import lombok.Data;

@Data
public class ContestQuestionUser {
    int contestQuestionID;

    String userMail;

    String ACTime;

    int inputNum;

    int acceptNum;

    int waNum;
}
