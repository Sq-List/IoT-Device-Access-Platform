package com.sqlist.web.exception;

import com.sqlist.web.result.CodeMsg;

/**
 * @author SqList
 * @date 2019/4/9 9:44
 * @descriptio
 **/
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;

    private Object data;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }

    public GlobalException(CodeMsg codeMsg, Object data) {
        this(codeMsg);
        this.data = data;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public Object getData() {
        return data;
    }
}
