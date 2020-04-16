package com.origincat.oj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgeResult {

    private String submitID;
    private Integer status;
    private Integer timeUsed;
    private Integer memoryUsed;
    private String errorMessage;

}
