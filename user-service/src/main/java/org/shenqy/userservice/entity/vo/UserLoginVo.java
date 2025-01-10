package org.shenqy.userservice.entity.vo;

import lombok.Data;

@Data
public class UserLoginVo {
    private Integer userId;
    private String profile;
    private String token;
    private String username;
}
