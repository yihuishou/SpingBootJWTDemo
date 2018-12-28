package com.mapper;

import com.model.Power;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PowerMapper extends Mapper<Power> {

    @Select("SELECT p.action FROM account_user_role ar,user_role ur,power p,user_role_power up WHERE ar.userRoleID=ur.userRoleID AND ur.userRoleID=up.userRoleID AND up.powerID=p.ID AND ar.accountID=#{accountID}")
    String getUserPowerInfo(Long accountID);

    @Select("SELECT*FROM power p LEFT JOIN user_role_power up ON p.ID=up.powerID WHERE up.userRoleID=#{roleID}")
    Power getPower(Integer roleID);


}