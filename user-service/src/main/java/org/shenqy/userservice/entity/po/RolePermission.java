package org.shenqy.userservice.entity.po;


import lombok.Data;

@Data
public class RolePermission {
    private Integer roleId;
    private Byte permissionCode;
}
