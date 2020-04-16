package com.origincat.oj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class JudgeTask {

    private String submitID;

    private int compilerID;

    private String questionID;

    private int questionNum;

    private String userMail;

    private String source;

    private int timeLimit;

    private int memoryLimit;
}