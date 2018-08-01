package com.reizx.luaj.view.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.reizx.luaj.app.App;
import com.reizx.luaj.di.component.ActivityComponent;
import com.reizx.luaj.di.component.DaggerActivityComponent;
import com.reizx.luaj.di.module.ActivityModule;
import com.reizx.luaj.presenter.common.IBasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Kigkrazy on 2017/7/19.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements BaseView {
    protected Activity activity;//当前Activity的上下文
    QMUITipDialog tipDialog;

    @Inject
    protected T presenter;

    @Inject
    protected App app;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());//设置主布局ID
        ButterKnife.bind(this);//添加注解在注入，一定要在setContentView之后使用
        init();//初始化全局的一些变量
        initInject();//注入依赖
        initAllMembersView();//初始化一些其他视图对象
        presenter.attachView(this);
        onCreateFinish();//onCreate完成时候调用
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.detachView();
    }

    /**
     * 获取当前ActivityModule
     *
     * @return
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * 初始化全局变量
     */
    protected void init() {
        activity = this;
    }

    /**
     * 返回主布局id
     */
    protected abstract int getLayoutID();

    /**
     * 初始化其他显示VIEW
     */
    public void initAllMembersView() {
    }

    /**
     * 注入依赖
     */
    protected abstract void initInject();

    /**
     * onCreate完成时候调用，一般用于请求权限之类的操作
     */
    protected void onCreateFinish(){

    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    @Override
    public void toast(String msg) {
        ToastUtils.showLong(msg);
    }

    @Override
    public void showTip(int iconType, @NonNull String tipWord) {
        tipDialog = new QMUITipDialog.Builder(app)
                .setTipWord(tipWord)
                .setIconType(iconType)
                .create();
        tipDialog.show();
    }

    @Override
    public void showTip(@NonNull String tipWord) {
        tipDialog = new QMUITipDialog.Builder(app)
                .setTipWord(tipWord)
                .create();
        tipDialog.show();
    }

    @Override
    public void dismissTip() {
        if (tipDialog != null && tipDialog.isShowing())
            tipDialog.dismiss();
    }
}
