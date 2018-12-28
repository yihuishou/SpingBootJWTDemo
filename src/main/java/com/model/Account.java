package com.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "account")
public class Account {
    /**
     * 用户账号
     */
    @Id
    @Column(name = "ID")
    private Long id;

    /**
     * 昵称
     */
    @Column(name = "name")
    private String name;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 创建人
     */
    @Column(name = "createID")
    private Integer createid;

    /**
     * 创建时间
     */
    @Column(name = "createDateTime")
    private Date createdatetime;

    /**
     * 更新时间
     */
    @Column(name = "updateDateTime")
    private Date updatedatetime;

    /**
     * 用户状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 备注
     */
    @Column(name = "note")
    private String note;

    /**
     * 获取用户账号
     *
     * @return ID - 用户账号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户账号
     *
     * @param id 用户账号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取昵称
     *
     * @return name - 昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置昵称
     *
     * @param name 昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取用户密码
     *
     * @return password - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取创建人
     *
     * @return createID - 创建人
     */
    public Integer getCreateid() {
        return createid;
    }

    /**
     * 设置创建人
     *
     * @param createid 创建人
     */
    public void setCreateid(Integer createid) {
        this.createid = createid;
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

    /**
     * 获取更新时间
     *
     * @return updateDateTime - 更新时间
     */
    public Date getUpdatedatetime() {
        return updatedatetime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedatetime 更新时间
     */
    public void setUpdatedatetime(Date updatedatetime) {
        this.updatedatetime = updatedatetime;
    }

    /**
     * 获取用户状态
     *
     * @return status - 用户状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置用户状态
     *
     * @param status 用户状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return note - 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注
     *
     * @param note 备注
     */
    public void setNote(String note) {
        this.note = note;
    }
}