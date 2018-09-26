package com.model;

import javax.persistence.*;

@Table(name = "role_prower")
public class RolePrower {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "powerID")
    private Integer powerid;

    @Column(name = "roleID")
    private Integer roleid;

    /**
     * @return id
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
     * @return powerID
     */
    public Integer getPowerid() {
        return powerid;
    }

    /**
     * @param powerid
     */
    public void setPowerid(Integer powerid) {
        this.powerid = powerid;
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