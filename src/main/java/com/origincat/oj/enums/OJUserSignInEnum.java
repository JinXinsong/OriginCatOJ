package com.origincat.oj.enums;

import lombok.Getter;

public enum OJUserSignInEnum {
    ERROR(0, "fail"),
    STUDENT(1, "student sign in"),
    ADMIN(2, "admin sign in"),
    CHECK(3,"checking");

    @Getter
    private int state;

    @Getter
    private String stateInfo;

    private OJUserSignInEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static OJUserSignInEnum stateOf(int state){
        for(OJUserSignInEnum stateEnum : values()){
            if(stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
