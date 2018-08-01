package com.reizx.luaj.contract;

import com.reizx.luaj.presenter.common.IBasePresenter;
import com.reizx.luaj.view.common.BaseView;

public class SettingContract {
    public interface View extends BaseView {
        void showIpStatus(String msg);
    }

    public interface  Presenter extends IBasePresenter<View> {
    }
}
