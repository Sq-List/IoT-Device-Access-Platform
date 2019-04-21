package com.sqlist.web.result;

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
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");

    /**
     * 登录模块 5002XX
     */
    public static CodeMsg SESSION_ERROR = new CodeMsg(500201, "Session不存在或者已经失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500202, "登录密码不能为空");
    public static CodeMsg LOGIN_ERROR = new CodeMsg(500203, "用户名或密码错误");

    /**
     * Task 5003XX
     */
    public static CodeMsg TASK_NAME_REPEAT = new CodeMsg(500301, "任务名称不能重复");

    private CodeMsg( ) {
    }

    private CodeMsg( int code,String msg ) {
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
