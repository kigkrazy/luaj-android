package com.reizx.luaj.contract;

import com.reizx.luaj.presenter.common.IBasePresenter;
import com.reizx.luaj.view.common.BaseView;

public class MainContract {
    public interface View extends BaseView {

    }

    public interface Presenter extends IBasePresenter<View> {

    }
}
