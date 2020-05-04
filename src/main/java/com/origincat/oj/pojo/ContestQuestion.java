package com.origincat.oj.pojo;

import lombok.Data;

@Data
public class ContestQuestion {
    int contestQuestionID;

    String contestID;

    int questionNum;

    int inputNum;

    int AcceptNum;
}