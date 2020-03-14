package com.origincat.oj.enums;

import lombok.Getter;

public enum StudentSignUpEnum {
    SUCCESS(0, "添加成功"),
    ERROR(1,"添加失败"),
    CHECK(2,"审核中");

    @Getter
    private int state;

    @Getter
    private String stateInfo;

    private StudentSignUpEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static StudentSignUpEnum stateOf(int state){
        for(StudentSignUpEnum stateEnum : values()){
            if(stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
