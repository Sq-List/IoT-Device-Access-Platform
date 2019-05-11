package com.sqlist.admin.result;

import lombok.ToString;

/**
 * @author SqList
 * @date 2019/4/9 9:44
 * @descriptio
 **/
@ToString
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg == null) {
            return;
        }

        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    private Result(CodeMsg codeMsg, T data) {
        this(codeMsg);
        this.data = data;
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功时返回
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 失败时返回
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
    }

    public static <T> Result<T> fail(CodeMsg codeMsg, T data) {
        return new Result<T>(codeMsg, data);
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
