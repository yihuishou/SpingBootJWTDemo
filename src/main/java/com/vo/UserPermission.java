package com.vo;

import java.util.Date;
import java.util.Set;

/**
 * Created by LadyLady on 2018-09-19.
 */
public class UserPermission {

    private Long id;

    private String name;

    private String username;

    private String password;

    private Integer createid;

    private Date createdatetime;

    private Date updatedatetime;

    private String status;

    private String note;

    private Set<String> userRoleSet;

    private Set<String> powerSet;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
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

    public Integer getCreateid() {

        return createid;
    }

    public void setCreateid(Integer createid) {

        this.createid = createid;
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

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getNote() {

        return note;
    }

    public void setNote(String note) {

        this.note = note;
    }

    public Set<String> getUserRoleSet() {

        return userRoleSet;
    }

    public void setUserRoleSet(Set<String> userRoleSet) {

        this.userRoleSet = userRoleSet;
    }

    public Set<String> getPowerSet() {

        return powerSet;
    }

    public void setPowerSet(Set<String> powerSet) {

        this.powerSet = powerSet;
    }
}

