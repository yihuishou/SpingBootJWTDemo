package com.model;

import javax.persistence.*;

@Table(name = "power")
public class Power {
    /**
     * 权限ID
     */
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 许可操作
     */
    @Column(name = "action")
    private String action;

    /**
     * 权限名
     */
    @Column(name = "name")
    private String name;

    /**
     * 权限描述
     */
    @Column(name = "detail")
    private String detail;

    /**
     * 获取权限ID
     *
     * @return ID - 权限ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置权限ID
     *
     * @param id 权限ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取许可操作
     *
     * @return action - 许可操作
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置许可操作
     *
     * @param action 许可操作
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取权限名
     *
     * @return name - 权限名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置权限名
     *
     * @param name 权限名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取权限描述
     *
     * @return detail - 权限描述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置权限描述
     *
     * @param detail 权限描述
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}