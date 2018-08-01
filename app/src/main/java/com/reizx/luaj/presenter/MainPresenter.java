package com.reizx.luaj.presenter;

import com.reizx.luaj.contract.MainContract;
import com.reizx.luaj.model.DataManager;
import com.reizx.luaj.presenter.common.BasePresenterImpl;

import javax.inject.Inject;

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {
    @Inject
    public MainPresenter(DataManager dm) {
        super(dm);
    }
}
