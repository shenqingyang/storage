package org.shenqy.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.shenqy.userservice.entity.dto.LoginFormDto;
import org.shenqy.userservice.entity.dto.RegisterFormDto;
import org.shenqy.userservice.entity.vo.UserLoginVo;
import org.shenqy.userservice.service.IUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "body参数")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

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
}
