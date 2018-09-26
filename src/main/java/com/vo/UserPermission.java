package com.vo;

import java.util.Date;
import java.util.Set;

/**
 * Created by LadyLady on 2018-09-19.
 */
public class UserPermission {

    private Integer account;

    private String username;

    private String password;

    private String uuid;

    private Date createdatetime;

    private Date updatedatetime;

    private Set<String> roleSet;

    private Set<String> powerSet;

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public Date getUpdatedatetime() {
        return updatedatetime;
    }

    public void setUpdatedatetime(Date updatedatetime) {
        this.updatedatetime = updatedatetime;
    }

    public Set<String> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<String> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<String> getPowerSet() {
        return powerSet;
    }

    public void setPowerSet(Set<String> powerSet) {
        this.powerSet = powerSet;
    }
}
