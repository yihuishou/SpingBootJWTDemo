package com.mapper;

import com.model.Account;
import com.vo.UserInfo;
import com.vo.UserPermission;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AccountMapper extends Mapper<Account> {

    @Select("SELECT*FROM account a WHERE a.ID=#{accountID}")
    @Results({@Result(property = "id", column = "ID"), @Result(property = "userRoleSet", column = "ID", many = @Many(select = "com.mapper.UserRoleMapper.getUserRoleInfo")), @Result(property = "powerSet", column = "ID", many = @Many(select = "com.mapper.PowerMapper.getUserPowerInfo"))})
    public UserPermission getUserPowerInfo(Long accountID);

    @Select("SELECT*FROM account a WHERE a.ID=#{accountID}")
    @Results({@Result(property = "id", column = "ID"), @Result(property = "userRoleList", column = "ID", many = @Many(select = "com.mapper.UserRoleMapper.getRoleInfo"))})
    public UserInfo getUserInfo(Long accountID);
}