package org.shenqy.userservice.entity.vo;

import lombok.Data;
import org.shenqy.userservice.entity.po.Company;
import org.shenqy.userservice.entity.po.Store;
import org.shenqy.userservice.entity.po.Warehouse;

import java.util.List;

@Data
public class UserVo {
    private Integer userId;
    private String userName;
    private String gender;
    private String phone;
    private String profile;
    private String department;
    private String post;
    private Warehouse warehouse;
    private Store store;
    private Company company;
    private List<RoleVo> roles;
}
