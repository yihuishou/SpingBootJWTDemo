package com.model;

import javax.persistence.*;

@Table(name = "account_user_role")
public class AccountUserRole {
    @Column(name = "accountID")
    private Long accountid;

    @Column(name = "userRoleID")
    private Integer userroleid;

    /**
     * @return accountID
     */
    public Long getAccountid() {
        return accountid;
    }

    /**
     * @param accountid
     */
    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    /**
     * @return userRoleID
     */
    public Integer getUserroleid() {
        return userroleid;
    }

    /**
     * @param userroleid
     */
    public void setUserroleid(Integer userroleid) {
        this.userroleid = userroleid;
    }
}