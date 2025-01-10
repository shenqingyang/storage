package org.shenqy.userservice.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String password;
    private String loginAccount;
    private String phone;
    private String profile;
    private String department;
    private String post;
    private Integer warehouseId;
    private Integer storeId;
    private Integer companyId;
    @TableLogic //逻辑删除
    private Integer deleted;
}
