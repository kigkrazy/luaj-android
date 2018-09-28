--
-- JAVA层返回数组的测试用例
-- Created by IntelliJ IDEA.
-- User: kig
-- Date: 2018/9/28
-- Time: 18:27
-- To change this template use File | Settings | File Templates.
--

-- 引入类
LuajLog = luajava.bindClass("com.reizx.luaj.component.LuajLog") --Log打印类
LuajAppUtil = luajava.bindClass("com.reizx.luaj.component.LuajAppUtil") --Log打印类
ListReturn = luajava.bindClass("com.reizx.luaj.component.ListReturn") --Log打印类

LuajLog:d("===========================================================")
LuajLog:d("ListReturnExample test")
LuajLog:d("===========================================================")

local string_list = ListReturn:getStringTest()
LuajLog:d("type of string_list : "..type(string_list))

