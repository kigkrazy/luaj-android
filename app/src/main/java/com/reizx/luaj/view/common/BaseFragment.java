package com.reizx.luaj.view.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.reizx.luaj.app.App;
import com.reizx.luaj.bean.event.TipEvent;
import com.reizx.luaj.component.RxBus;
import com.reizx.luaj.di.component.DaggerFragmentComponent;
import com.reizx.luaj.di.component.FragmentComponent;
import com.reizx.luaj.di.module.FragmentModule;
import com.reizx.luaj.presenter.common.IBasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Kigkrazy on 2017/7/31.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements BaseView{
    protected BaseActivity baseActivity;
    protected View rootView;
    QMUITipDialog tipDialog;
    private Unbinder unbinder;//ButterKnife 解绑对象

    @Inject
    protected T presenter;

    @Inject
    protected App app;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getFragmentLayoutID(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        baseActivity = (BaseActivity) getActivity();
        initInject();//设置presenter注入
        initAllMembersView();//初始化一些VIEW对象
        presenter.attachView(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 去除绑定，避免fragment恢复的是时候view被解绑
        //todo 此处会不会造成内存泄露？保持疑问！！因为ButterKnife理论上回随着view销毁,所以此处保留意见
        unbinder.unbind();
    }

    /**
     * 获取ID
     *
     * @return
     */
    public abstract int getFragmentLayoutID();

    /**
     * 初始化视图
     *
     * @return
     */
    public void initAllMembersView(){

    }

    /**
     * 注入依赖
     */
    protected abstract void initInject();

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
        tipDialog = new QMUITipDialog.Builder(getActivity())
                .setTipWord(tipWord)
                .setIconType(iconType)
                .create();
        tipDialog.show();
    }

    @Override
    public void showTip(@NonNull String tipWord) {
        tipDialog = new QMUITipDialog.Builder(getActivity())
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
