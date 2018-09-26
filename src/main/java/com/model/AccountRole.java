package com.model;

import javax.persistence.*;

@Table(name = "account_role")
public class AccountRole {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Uuid")
    private String uuid;

    @Column(name = "roleID")
    private Integer roleid;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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
}