# 关于LIST的相关问题

## 支持的数组返回形式
目前不支持`List`与`ArrayList`,只支持数组(String[])形式的返回值。

## 获取数组长度
```lua
LuajLog:d("type of string_list length: "..#string_list)
```

## 遍历数组
参考例子(ListReturnExample.lua)
```lua
LuajLog:d('show all element #1 ')
for i=1, #string_list do
    LuajLog:d(string.format('string_list[%d] : '..string_list[i], i))
end
```
----------------------------------------------------------

`ListReturnExample.lua`内容牛下
```lua
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

```