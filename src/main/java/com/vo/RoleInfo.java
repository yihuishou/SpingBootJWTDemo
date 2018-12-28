package com.vo;

import com.model.Power;

import java.util.Date;
import java.util.List;

/**
 * Created by LadyLady on 2018-12-28.
 */
public class RoleInfo {

    private Integer userroleid;

    private String name;

    private String detail;

    private String status;

    private Date createdatetime;

    private List<Power> powerList;

    public List<Power> getPowerList() {

        return powerList;
    }

    public void setPowerList(List<Power> powerList) {

        this.powerList = powerList;
    }

    public Integer getUserroleid() {

        return userroleid;
    }

    public void setUserroleid(Integer userroleid) {

        this.userroleid = userroleid;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDetail() {

        return detail;
    }

    public void setDetail(String detail) {

        this.detail = detail;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public Date getCreatedatetime() {

        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {

        this.createdatetime = createdatetime;
    }
}
