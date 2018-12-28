package com.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user_role")
public class UserRole {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "userRoleID")
    private Integer userroleid;

    /**
     * 角色名
     */
    @Column(name = "name")
    private String name;

    /**
     * 角色描述
     */
    @Column(name = "detail")
    private String detail;

    /**
     * 角色状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "createDateTime")
    private Date createdatetime;

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
     * 获取角色名
     *
     * @return name - 角色名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名
     *
     * @param name 角色名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色描述
     *
     * @return detail - 角色描述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置角色描述
     *
     * @param detail 角色描述
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取角色状态
     *
     * @return status - 角色状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置角色状态
     *
     * @param status 角色状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return createDateTime - 创建时间
     */
    public Date getCreatedatetime() {
        return createdatetime;
    }

    /**
     * 设置创建时间
     *
     * @param createdatetime 创建时间
     */
    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }
}