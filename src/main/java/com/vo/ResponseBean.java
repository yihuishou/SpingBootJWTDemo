package com.vo;

import java.util.List;

/**
 * Created by LadyLady on 2018-09-12.
 */
public class ResponseBean<T> {

    public ResponseBean() {

        super();
    }

    public ResponseBean(Integer responseCode, String message, Object data, List<T> listDate) {

        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
        this.listDate = listDate;
    }

    private String message;

    private Integer responseCode;

    private Object data;

    private List<T> listDate;

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public Integer getResponseCode() {

        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {

        this.responseCode = responseCode;
    }

    public Object getData() {

        return data;
    }

    public void setData(Object data) {

        this.data = data;
    }

    public List<T> getListDate() {

        return listDate;
    }

    public void setListDate(List<T> listDate) {

        this.listDate = listDate;
    }
}
