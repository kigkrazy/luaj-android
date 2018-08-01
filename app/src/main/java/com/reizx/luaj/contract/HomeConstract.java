package com.reizx.luaj.contract;

import android.content.Context;

import com.reizx.luaj.presenter.common.IBasePresenter;
import com.reizx.luaj.view.common.BaseView;

/**
 * 主页的借口约定类
 */
public class HomeConstract {
    public interface View extends BaseView{
        /**
         * 设置当前IP
         * @param ip
         */
        public void setCurrentIp(String ip);
    }

    public interface Presenter extends IBasePresenter<View> {
        /**
         * 开始zookeeper的前台服务
         */
        void startZkService(Context context);

        /**
         * 开始zookeeper的前台服务
         */
        void stopZkService(Context context);

        /**
         * 绑定zookeeper的服务（用于测试）
         */
        void bindZkService();

        /**
         * 请求zookeeper的接口（用于测试）
         */
        void callHelloZkService();

        /**
         * 从IP138接口请求，并且显示当前IP
         */
        void showCurrentIp();
    }
}
