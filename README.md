# 安卓项目初始化框架
本框架是为了方便开发，整合一些最基本的功能。在开发新项目的时候可以基于这个项目进行修改。

## 项目介绍
项目引用了几个大框架`Design + MVP + RxJava2 + Retrofit + Dagger2 + QMUI`进行项目初始化搭建。

## 使用方法
直接下载项目，修改项目`包名`，`项目名`，`applicationId`等关键信息就可以进行开发了。

### 添加Retrofit2请求接口(此处我们以`IpApi`为例)
1. 在`com.reizx.asf.model.retrofit.api`包下建立相关接口类(`IpApi`)
2. 在`com.reizx.ares.mgr.model.DataManager#createAllApi`的构造函数添加对应API的实例创建
```java
    private UpdateApi updateApi;//此处声明

    public DataManager(Retrofit.Builder builder, OkHttpClient client){
        createAllApi(builder, client);
    }

    /**
     * 创建所有API实例
     * @param builder
     * @param client
     */
    public void createAllApi(Retrofit.Builder builder, OkHttpClient client) {
        // todo 此处添加所有需要的API
        updateApi = createRetrofit(builder, client, UpdateApi.HOST).create(UpdateApi.class);//更新信息请求接口
    }
```
**后续如果有其他类似`Sqlite`或者`sharedpreferences`管理接口也可以直接写在`DataManager`中。**

## 项目规范
[安卓个人开发规范开发规范](https://kigkrazy.github.io/android/2018/01/11/android-develop-framework/)

## 相关依赖库推荐
[andrutil](https://github.com/kigkrazy/andrutil)  

## 更新
[更新日志](UPDATE_LOG.md)