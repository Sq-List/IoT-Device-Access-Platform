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

//    private LuaValue func;
//
//    public LuaUtil(String luaFilePath) {
//
//    }

    public static String operate(String luaFilePath, String data) {
        Globals globals = JsePlatform.standardGlobals();
        globals.loadfile(luaFilePath).call();
        LuaValue func = globals.get(LuaValue.valueOf("operate"));
        return func.call(data).tojstring();
    }
}
