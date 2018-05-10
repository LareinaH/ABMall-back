package com.cotton.base.common;

import java.io.Serializable;

/**
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */

public class RestResponse<T> implements Serializable {

    private static final long serialVersionUID = 4843066638830850455L;

    private int code;
    private String message;
    private T data;

    //成功
    private static  int SUCCESS = 200;

    //未登录
    private static  int UNAUTHORIZED = 401;


    public RestResponse() {
    }

    public RestResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResponse(T data) {
        this.code = SUCCESS;
        this.data = data;
    }

    public boolean getSuccessed(){

        return code == SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestResponse [code=" + code + ", message="
                + message + ", data=" + data + "]";
    }

}
