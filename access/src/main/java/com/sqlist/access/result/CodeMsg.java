package com.sqlist.access.result;

/**
 * @author SqList
 * @date 2019/4/9 9:44
 * @descriptio
 **/
public class CodeMsg {

    private int code;
    private String msg;

    /**
     * 通用的错误码
     */
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");

    /**
     * session模块 5001XX
     */
    public static CodeMsg LOGIN_FAIL = new CodeMsg(500101, "上线失败");

    private CodeMsg() {
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }
}
