package com.mapper;

import com.model.Account;
import com.vo.UserPermission;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AccountMapper extends Mapper<Account> {

    @Select("SELECT * FROM account a WHERE a.Uuid = #{Uuid}")
    @Results({@Result(property = "uuid", column = "Uuid"), @Result(property = "roleSet", column = "Uuid", many = @Many(select = "com.mapper.RoleMapper.getRoleSet")), @Result(property = "powerSet", column = "Uuid", many = @Many(select = "com.mapper.PowerMapper.getPowerSet"))})
    public UserPermission findUserPermissionByUuid(String Uuid);
}