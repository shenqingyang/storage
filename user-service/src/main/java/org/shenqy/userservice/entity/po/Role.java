package org.shenqy.userservice.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer roleId;
    private String name;
    private Integer companyId;
    @TableLogic
    private Integer deleted;
}
