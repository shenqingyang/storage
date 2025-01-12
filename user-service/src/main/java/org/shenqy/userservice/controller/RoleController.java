package org.shenqy.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.shenqy.userservice.entity.dto.LoginFormDto;
import org.shenqy.userservice.entity.vo.UserLoginVo;
import org.shenqy.userservice.service.IRoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "角色权限相关接口")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

}
