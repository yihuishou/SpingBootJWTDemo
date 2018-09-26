package com.mapper;

import com.model.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface RoleMapper extends Mapper<Role> {

    @Select("SELECT r.roleName FROM account a , role r , account_role ar WHERE a.Uuid = ar.Uuid AND ar.roleID = r.roleID AND a.Uuid = #{Uuid}")
    public String  getRoleSet(Integer Uuid);
}