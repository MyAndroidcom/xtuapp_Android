package com.xtuapp.zsxd.domain;

/**
 * Created by Administrator on 2016/4/8 0008.
 */
public class Response {
    private int code;
    private String desc;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
