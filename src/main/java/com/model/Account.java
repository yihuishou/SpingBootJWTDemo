package com.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "account")
public class Account {
    @Id
    @Column(name = "account")
    private Integer account;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "Uuid")
    private String uuid;

    @Column(name = "createDateTime")
    private Date createdatetime;

    @Column(name = "updateDateTime")
    private Date updatedatetime;

    /**
     * @return account
     */
    public Integer getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(Integer account) {
        this.account = account;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return createDateTime
     */
    public Date getCreatedatetime() {
        return createdatetime;
    }

    /**
     * @param createdatetime
     */
    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    /**
     * @return updateDateTime
     */
    public Date getUpdatedatetime() {
        return updatedatetime;
    }

    /**
     * @param updatedatetime
     */
    public void setUpdatedatetime(Date updatedatetime) {
        this.updatedatetime = updatedatetime;
    }
}