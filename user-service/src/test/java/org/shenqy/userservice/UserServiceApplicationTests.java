package org.shenqy.userservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.shenqy.userservice.entity.po.User;
import org.shenqy.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest
class UserServiceApplicationTests {
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    private IUserService userservice;
    @Test
    void contextLoads() {
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.lambda().eq(User::getLoginAccount,"shenqy");
//        User user = userservice.lambdaQuery().eq(User::getLoginAccount,"shenqy").one();
        Configuration configuration = sqlSessionFactory.getConfiguration();

        Map<String, Object> params = Collections.singletonMap("ew", ew);

        // 使用类引用+方法名得到 mapper 语句
        MappedStatement mappedStatement = configuration.getMappedStatement("org.shenqy.userservice.mapper.UserMapper.selectList");
        BoundSql boundSql = mappedStatement.getBoundSql(params);

        String sql = getExecuteSql(boundSql, params);
        System.out.println(sql);
    }
    private String getExecuteSql(BoundSql boundSql, Object paramObject) {
        // 带有问号占位符的 SQL 语句
        String sql = boundSql.getSql();
        // 参数信息列表
        List<ParameterMapping> paramMappings = boundSql.getParameterMappings();
        // MetaObject 是 mybatis 通过表达式取出对象内容的工具
        MetaObject metaObject = sqlSessionFactory.getConfiguration().newMetaObject(paramObject);
        for (ParameterMapping p : paramMappings) {
            String paramName = p.getProperty();
            Object paramValue = metaObject.getValue(paramName);
            String value = "";
            if (paramValue instanceof String) {
                value = "'" + paramValue + "'";
            } else {
                // todo 其他类型的参数的对应的拼接方式
            }
            sql = sql.replaceFirst("\\?", value);
        }
        return sql;
    }
}
