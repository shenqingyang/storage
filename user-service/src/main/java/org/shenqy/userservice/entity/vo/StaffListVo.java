package org.shenqy.userservice.entity.vo;

import lombok.Data;

import java.util.List;


@Data
public class StaffListVo {
    private Integer userId;
    private String userName;
    private String gender;
    private String phone;
    private List<RoleVo> roles;
}
