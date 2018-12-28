package com.model;

import javax.persistence.*;

@Table(name = "user_role_power")
public class UserRolePower {
    /**
     * 角色ID
     */
    @Column(name = "userRoleID")
    private Integer userroleid;

    /**
     * 权限ID
     */
    @Column(name = "potenceID")
    private Integer potenceid;

    /**
     * 获取角色ID
     *
     * @return userRoleID - 角色ID
     */
    public Integer getUserroleid() {
        return userroleid;
    }

    /**
     * 设置角色ID
     *
     * @param userroleid 角色ID
     */
    public void setUserroleid(Integer userroleid) {
        this.userroleid = userroleid;
    }

    /**
     * 获取权限ID
     *
     * @return potenceID - 权限ID
     */
    public Integer getPotenceid() {
        return potenceid;
    }

    /**
     * 设置权限ID
     *
     * @param potenceid 权限ID
     */
    public void setPotenceid(Integer potenceid) {
        this.potenceid = potenceid;
    }
}