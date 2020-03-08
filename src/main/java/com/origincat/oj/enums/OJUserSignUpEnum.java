package com.origincat.oj.enums;

import lombok.Getter;

public enum OJUserSignUpEnum {
    CHECK(0, "审核中"),
    SUCCESS(1, "注册成功"),
    ERROR(2, "注册失败");

    @Getter
    private int state;

    @Getter
    private String stateInfo;

    private OJUserSignUpEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static OJUserSignUpEnum stateOf(int state){
        for(OJUserSignUpEnum stateEnum : values()){
            if(stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
