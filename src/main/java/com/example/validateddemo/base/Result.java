package com.example.validateddemo.base;

/**
 * 接口调用结果封装类，包含操作结果码，操作结果文言，返回数据体
 * @author He Changjie on 2020/9/5
 */
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
