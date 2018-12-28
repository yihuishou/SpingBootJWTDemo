package com.mapper;

import com.model.UserRole;
import com.vo.RoleInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserRoleMapper extends Mapper<UserRole> {

    @Select("SELECT ur.`name` FROM user_role ur LEFT JOIN account_user_role ar ON ur.userRoleID=ar.userRoleID WHERE ar.accountID=#{accountID}")
    String getUserRoleInfo(Long accountID);

    @Select("SELECT*FROM account_user_role ar LEFT JOIN user_role ur ON ar.userRoleID=ur.userRoleID WHERE ar.accountID=#{accountID}")
    @Results({@Result(property = "userroleid", column = "userroleID"), @Result(property = "powerList", column = "userroleID", many = @Many(select = "com.mapper.PowerMapper.getPower"))})
    RoleInfo getRoleInfo(Long accountID);
}