package org.shenqy.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.shenqy.userservice.entity.dto.LoginFormDto;
import org.shenqy.userservice.entity.dto.RegisterFormDto;
import org.shenqy.userservice.entity.po.User;
import org.shenqy.userservice.entity.vo.UserLoginVo;

public interface IUserService extends IService<User>{
    UserLoginVo login(LoginFormDto loginDTO);
    Boolean register(RegisterFormDto registerDto);
}
