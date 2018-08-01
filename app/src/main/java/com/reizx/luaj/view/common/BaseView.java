package com.reizx.luaj.view.common;

import android.support.annotation.NonNull;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

public interface BaseView {

    /**
     * 返回界面错误信息，并处理
     * @param msg
     */
    void showErrorMsg(String msg);

    //=======  State  =======

    /**
     * 界面显示出错时候处理
     * 例如：
     * 1. 界面加载失败
     * 2. 网络请求失败时候处理
     */
    void stateError();


    /**
     * 界面为空，时候处理
     */
    void stateEmpty();

    /**
     * 界面正在加载
     */
    void stateLoading();

    /**
     * 界面状态执行
     */
    void stateMain();

    /**
     * Toast msg
     */
    void toast(String msg);

    /**
     * 显示提示
     * @param iconType QMUI的几种内置的图标类型, 不显示请置为 -1
     * @param tipWord 提示语
     */
    void showTip(@QMUITipDialog.Builder.IconType int iconType, @NonNull String tipWord);


    /**
     * 显示提示
     * @param tipWord 提示语
     */
    void showTip(@NonNull String tipWord);


    /**
     * 解除提示
     */
    void dismissTip();
}
