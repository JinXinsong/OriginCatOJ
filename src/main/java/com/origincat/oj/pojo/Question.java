package com.origincat.oj.pojo;

import lombok.Data;

@Data
public class Question {
    private String questionID;

    private String questionTitle;

    private int questionInput;

    private int questionAccept;

    private int questionStatus;

    private int questionTimeLimit;

    private int questionMemoryLimit;

    private String questionContent;

    private String questionInputSimple;

    private String questionOutputSimple;
    
    private String questionPrompt;
}
