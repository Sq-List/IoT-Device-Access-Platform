package com.sqlist.result;

/**
 * @author SqList
 * @date 2019/4/9 9:44
 * @descriptio
 **/
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
