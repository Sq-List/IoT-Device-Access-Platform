package com.sqlist.util;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.Serializable;

/**
 * @author SqList
 * @date 2019/5/7 22:47
 * @description
 **/
public class LuaUtil implements Serializable {

    private static String transferOperatePath = "/usr/local/flink-1.7.2/lua/transferOperate.lua";
    private static String transferOperateMethod = "transferOperate";

    public static String operate(String luaFilePath, String data) {
        Globals globals = JsePlatform.standardGlobals();
        globals.loadfile(transferOperatePath).call();
        LuaValue func = globals.get(LuaValue.valueOf(transferOperateMethod));
        return func.call(LuaValue.valueOf(luaFilePath), LuaValue.valueOf(data)).tojstring();
    }
}
