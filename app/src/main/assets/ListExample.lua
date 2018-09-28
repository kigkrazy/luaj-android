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
LuajLog:d("ListExample test")
LuajLog:d("===========================================================")

-- 获取数组格式的返回值
local string_list = ListReturn:getStringTest()
LuajLog:d("type of string_list type: "..type(string_list))
-- 获取数组长度
LuajLog:d("type of string_list length: "..#string_list)
-- 遍历数组
LuajLog:d('show all element #1 ')
for i=1, #string_list do
    LuajLog:d(string.format('string_list[%d] : '..string_list[i], i))
end
