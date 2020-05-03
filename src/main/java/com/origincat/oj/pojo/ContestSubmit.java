package com.origincat.oj.pojo;

import lombok.Data;

@Data
public class ContestSubmit {
    int contestQuestionID;

    String userMail;

    int inputNum;

    int acceptNum;

    int waNum;
}
