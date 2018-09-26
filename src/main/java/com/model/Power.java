package com.model;

import javax.persistence.*;

@Table(name = "power")
public class Power {
    @Id
    @Column(name = "powerID")
    private Integer powerid;

    @Column(name = "powerName")
    private String powername;

    @Column(name = "powerDetials")
    private String powerdetials;

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
     * @return powerName
     */
    public String getPowername() {
        return powername;
    }

    /**
     * @param powername
     */
    public void setPowername(String powername) {
        this.powername = powername;
    }

    /**
     * @return powerDetials
     */
    public String getPowerdetials() {
        return powerdetials;
    }

    /**
     * @param powerdetials
     */
    public void setPowerdetials(String powerdetials) {
        this.powerdetials = powerdetials;
    }
}