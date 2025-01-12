package org.shenqy.userservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.shenqy.storagecommon.entity.PageDTO;
import org.shenqy.storagecommon.exception.BadRequestException;
import org.shenqy.userservice.entity.dto.LoginFormDto;
import org.shenqy.userservice.entity.dto.RegisterFormDto;
import org.shenqy.userservice.entity.po.User;
import org.shenqy.userservice.entity.query.StaffQuery;
import org.shenqy.userservice.entity.vo.StaffListVo;
import org.shenqy.userservice.entity.vo.UserLoginVo;
import org.shenqy.userservice.entity.vo.UserVo;
import org.shenqy.userservice.service.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "用户相关接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final IRoleService roleService;
    private final ICompanyService companyService;
    private final IStoreService storeService;
    private final IWarehouseService warehouseService;

    @Operation(summary = "用户登录")
    @PostMapping("login")
    public UserLoginVo login(@RequestBody @Validated LoginFormDto loginFormDto){
        return userService.login(loginFormDto);
    }

    @Operation(summary = "用户注册")
    @PostMapping("register")
    public Boolean register(@RequestBody @Validated RegisterFormDto registerFormDto){

        return userService.register(registerFormDto);
    }

    @Operation(summary = "获得公司员工信息")
    @GetMapping("/page/company}")
    public PageDTO<StaffListVo> getCompanyStaffs(StaffQuery query){
        if(query.getCompanyId() == null)
            throw new BadRequestException("未提供公司id");

        Page<User> result = userService.lambdaQuery()
                .eq(User::getCompanyId, query.getCompanyId())
                .page(query.toMpPage());
        Page<StaffListVo> ret = new Page<StaffListVo>();
        List<User> users = result.getRecords();
        List<StaffListVo> staffs = new ArrayList<>();
        for (User user : users) {
            StaffListVo vo = new StaffListVo();
            vo.setUserId(user.getUserId());
            vo.setGender(user.getGender());
            vo.setPhone(user.getPhone());
            vo.setUserName(user.getUserName());
            vo.setRoles(roleService.selectRoleByUserId(user.getUserId()));
        }
        ret.setRecords(staffs);
        ret.setTotal(result.getTotal());
        ret.setSize(result.getSize());
        ret.setCurrent(result.getCurrent());
        // 2.封装并返回
        return PageDTO.of(result, StaffListVo.class);
    }
    @Operation(summary = "获得一个公司员工详细信息")
    @GetMapping("/one/{userId}")
    public UserVo getUser(@PathVariable @Validated Integer userId){
        User user = userService.getById(userId);
        UserVo vo = new UserVo();
        vo.setUserName(user.getUserName());
        vo.setPhone(user.getPhone());
        vo.setGender(user.getGender());
        vo.setUserId(userId);
        vo.setPost(user.getPost());
        vo.setDepartment(user.getDepartment());
        vo.setRoles(roleService.selectRoleByUserId(userId));
        return vo;
    }
}
