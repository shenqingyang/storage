package org.shenqy.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.shenqy.userservice.entity.po.Role;
import org.shenqy.userservice.entity.vo.RoleVo;
import org.shenqy.userservice.mapper.RoleMapper;
import org.shenqy.userservice.mapper.RolePermissionMapper;
import org.shenqy.userservice.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    private final RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RoleVo> selectRoleByUserId(Integer userId) {
        return baseMapper.selectRoleByUserId(userId);
    }
}
