package com.origincat.oj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgeResult {

    private String userMail;

    private String submitID;

    private int questionNum;

    private String questionTitle;

    private int status;

    private int timeUsed;

    private int memoryUsed;

    private String errorMessage;

    private String time;
}
