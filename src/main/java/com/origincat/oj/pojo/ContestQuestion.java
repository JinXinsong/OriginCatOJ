package com.origincat.oj.pojo;

import lombok.Data;

@Data
public class ContestQuestion {
    int contestQuestionID;

    String contestID;

    String questionID;

    int inputNum;

    int AcceptNum;
}