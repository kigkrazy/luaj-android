# luaj安卓框架
本项目由[luaj](https://github.com/luaj/luaj)的`jse`版本为基础修改而来。语法上与`luaj`并无不一样，主要修改了几处安卓平台上的引用错误。

## 使用
### 引入库

```
compile 'com.reizx:luaj-android:3.0.1'
```
### 使用例子
#### 执行文件（[demo](app\src\main\java\com\reizx\luaj\view\fragment\HomeFragment.java)）
```
    String path = "/sdcard/SimpleExample.lua";
    // copy the script to path
    ResourceUtils.copyFileFromAssets("SimpleExample.lua", path);
    // init global before
    // create an environment to run in
    // Globals globals = JsePlatform.standardGlobals();
    // Use the convenience function on Globals to load a chunk.
    LuaValue chunk = globals.loadfile(path);
    // Use any of the "call()" or "invoke()" functions directly on the chunk.
    chunk.invoke();
```

### 修改日志
* 修复`classloader`引用错误。