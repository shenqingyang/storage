package org.shenqy.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.shenqy.userservice.entity.po.Role;
import org.shenqy.userservice.entity.vo.RoleVo;

import java.util.List;

public interface IRoleService extends IService<Role> {
    List<RoleVo> selectRoleByUserId(Integer userId);
}
