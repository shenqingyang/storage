package org.shenqy.storagecommon.interceptors;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.shenqy.storagecommon.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;




public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.removeUser();
    }

    @Override
    public boolean preHandle(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        // 1.获取登录用户信息
        String userInfo = request.getHeader("user-info");
        // 2.判断是否获取了用户，如果有，存入ThreadLocal
        if (StrUtil.isNotBlank(userInfo)) {
            UserContext.setUser(Long.valueOf(userInfo));
        }
        // 3.放行
        return true;
    }

}
