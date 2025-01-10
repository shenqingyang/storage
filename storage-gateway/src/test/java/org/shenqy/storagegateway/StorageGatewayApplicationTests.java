package org.shenqy.storagegateway;

import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StorageGatewayApplicationTests {

    @Test
    void contextLoads() {
        String sql = "SELECT p.url\n" +
                "FROM permission p\n" +
                "JOIN role_permission rp ON p.permission_id = rp.permission_id\n" +
                "JOIN user_role ur ON rp.role_id = ur.role_id\n" +
                "WHERE ur.user_id = {0} AND ur.deleted = 0 AND rp.deleted = 0;";
        List<Object> urls = SqlRunner.db().selectObjs(sql,1);
        for (Object url : urls){
            System.out.println((String) url);
        }
    }

}

