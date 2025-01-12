package org.shenqy.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shenqy.storagecommon.exception.BadRequestException;
import org.shenqy.userservice.config.JwtProperties;
import org.shenqy.userservice.entity.dto.LoginFormDto;
import org.shenqy.userservice.entity.dto.RegisterFormDto;
import org.shenqy.userservice.entity.po.User;
import org.shenqy.userservice.entity.vo.UserLoginVo;
import org.shenqy.userservice.mapper.UserMapper;
import org.shenqy.userservice.service.IUserService;
import org.shenqy.userservice.utils.JwtTool;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTool jwtTool;
    private final JwtProperties jwtProperties;

    @Override
    public UserLoginVo login(LoginFormDto loginDTO) {
        // 1.数据校验
        String loginAccount = loginDTO.getLoginCount();
        String password = loginDTO.getPassword();
        // 2.根据用户名或手机号查询
        User user = lambdaQuery().eq(User::getLoginAccount, "shenqy").one();
        Assert.notNull(user, "用户名错误");
        // 4.校验密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("用户名或密码错误");
        }
        // 5.生成TOKEN
        String token = jwtTool.createToken((long)user.getUserId(), jwtProperties.getTokenTTL());
        // 6.封装VO返回
        UserLoginVo vo = new UserLoginVo();
        vo.setUserId(user.getUserId());
        vo.setUsername(user.getUserName());
        vo.setProfile(user.getProfile());
        vo.setToken(token);
        return vo;
    }

    @Override
    public Boolean register(RegisterFormDto registerDto) {
        User user = new User();
        user.setLoginAccount(registerDto.getLoginAccount());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUserName(registerDto.getUserName());
        user.setGender(registerDto.getGender());
        if(!registerDto.getProfile().isEmpty())
            user.setProfile(registerDto.getProfile());
        if(!registerDto.getPassword().isEmpty())
            user.setPhone(registerDto.getPhone());
        if(registerDto.getCompanyId() !=  0)
            user.setCompanyId(registerDto.getCompanyId());
        if(registerDto.getStoreId() !=  0)
            user.setStoreId(registerDto.getStoreId());
        if(registerDto.getWarehouseId() !=  0)
            user.setWarehouseId(registerDto.getWarehouseId());
        return save(user);
    }
}
