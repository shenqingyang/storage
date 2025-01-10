package org.shenqy.storagegateway.filters;

import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import lombok.RequiredArgsConstructor;
import org.shenqy.storagecommon.exception.UnauthorizedException;
import org.shenqy.storagegateway.config.AuthProperties;
import org.shenqy.storagegateway.util.JwtTool;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;

    private final JwtTool jwtTool;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取request
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        System.out.println(path);
        // 2.判断是否需要做登录拦截
        if(isExclude(path)){
            // 放行
            return chain.filter(exchange);
        }
        // 3.获取token
        String token = null;
        List<String> headers = request.getHeaders().get("authorization");
        if (headers != null && !headers.isEmpty()) {
            token = headers.get(0);
        }
        // 4.校验并解析token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
            if(userId == null || !isAuth(path,userId)){
                throw new UnauthorizedException("没有权限");
            }
        } catch (UnauthorizedException e)
        {
            // 拦截，设置响应状态码为401
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 5.传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();
        // 6.放行
        return chain.filter(swe);
    }

    private boolean isExclude(String path) {
        for (String pathPattern : authProperties.getExcludePaths()) {
            if (antPathMatcher.match(pathPattern, path)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAuth(String path,long userId){
        String sql = "SELECT p.url\n" +
                "FROM permission p\n" +
                "JOIN role_permission rp ON p.permission_id = rp.permission_id\n" +
                "JOIN user_role ur ON rp.role_id = ur.role_id\n" +
                "WHERE ur.user_id = ? AND ur.deleted = 0 AND rp.deleted = 0;";
        List<Object> urls = SqlRunner.db().selectObjs(sql,userId);
        for (Object pathPattern : urls) {
            if (antPathMatcher.match((String) pathPattern, path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
