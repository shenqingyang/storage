package org.shenqy.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.shenqy.userservice.entity.po.Role;
import org.shenqy.userservice.entity.vo.RoleVo;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    @Select("select r.role_id,r.name \n" +
            "from user_role ur left join `role` r \n" +
            "on ur.role_id = r.role_id\n" +
            "where ur.user_id = #{userId} ")
    List<RoleVo> selectRoleByUserId(@Param("userId") Integer userId);
}
