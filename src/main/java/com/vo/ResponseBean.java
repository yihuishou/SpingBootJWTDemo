package com.vo;

/**
 * Created by LadyLady on 2018-09-12.
 */
public class ResponseBean {

    public ResponseBean() {

        super();
    }

    public ResponseBean(Integer responseCode, String message, Object data) {

        this.responseCode = responseCode;
        this.message = message;
        this.data = data;

    }

    private String message;

    private Integer responseCode;

    private Object data;

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

}
