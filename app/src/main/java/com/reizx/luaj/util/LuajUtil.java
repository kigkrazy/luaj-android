package com.reizx.luaj.util;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuajUtil {
    public static Globals globals = JsePlatform.standardGlobals();

    /**
     * 执行lua文件
     * @param path
     */
    public static void execFile(String path) {
        try {
            LuaValue chunk = globals.loadfile(path);
            // Use any of the "call()" or "invoke()" functions directly on the chunk.
            chunk.invoke();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.d("exec file ");
        }
    }

}
