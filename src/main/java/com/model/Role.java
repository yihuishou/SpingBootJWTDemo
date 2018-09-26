package com.model;

import javax.persistence.*;

@Table(name = "role")
public class Role {
    @Id
    @Column(name = "roleID")
    private Integer roleid;

    @Column(name = "roleName")
    private String rolename;

    @Column(name = "roleDetials")
    private String roledetials;

    /**
     * @return roleID
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * @return roleName
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * @return roleDetials
     */
    public String getRoledetials() {
        return roledetials;
    }

    /**
     * @param roledetials
     */
    public void setRoledetials(String roledetials) {
        this.roledetials = roledetials;
    }
}