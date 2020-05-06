package com.origincat.oj.pojo;

import lombok.Data;

@Data
public class ContestUserRequest {
    private String contestUserID;

    private ContestUser contestUser;

    private OJUser ojUser;
}
