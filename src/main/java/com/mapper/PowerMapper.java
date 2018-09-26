package com.mapper;

import com.model.Power;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface PowerMapper extends Mapper<Power> {

    @Select("SELECT p.powerName FROM account a , role r ,power p , account_role ar , role_prower rp WHERE a.Uuid = ar.Uuid AND ar.roleID = r.roleID AND r.roleID = rp.roleID AND rp.powerID = p.powerID AND a.Uuid = #{Uuid}")
    public String  getPowerSet(String Uuid);
}