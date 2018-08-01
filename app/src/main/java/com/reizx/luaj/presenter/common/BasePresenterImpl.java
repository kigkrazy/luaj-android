package com.reizx.luaj.presenter.common;

import com.reizx.luaj.R;
import com.reizx.luaj.bean.event.TipEvent;
import com.reizx.luaj.component.RxBus;
import com.reizx.luaj.model.DataManager;
import com.reizx.luaj.view.common.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BasePresenterImpl<T extends BaseView> implements IBasePresenter<T> {
    protected T view;
    // CompositeDisposable是Disposable的容器，用来快速解除订阅，管理多个Disposable的生命周期
    public DataManager dm;// 数据管理

    protected CompositeDisposable compositeDisposable;

    public BasePresenterImpl(DataManager dm) {
        this.dm = dm;
    }

    /**
     * 解除订阅
     */
    protected void unSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    /**
     * 添加订阅
     *
     * @param subscription
     */
    protected void addSubscribe(Disposable subscription) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(subscription);
    }

    /**
     * 增加订阅
     *
     * @param eventType
     * @param act
     * @param <U>
     */
    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(RxBus.getInstance().toDefaultFlowable(eventType, act));
    }

    @Override
    public void attachView(T view) {
        this.view = view;
        registerEvent();//注册事件
    }

    @Override
    public void detachView() {
        this.view = null;
        unSubscribe();
    }

    /**
     * 注册事件
     */
    public void registerEvent(){
        addSubscribe(RxBus.getInstance().toFlowable(TipEvent.class)
                .subscribe(new Consumer<TipEvent>() {
                    @Override
                    public void accept(TipEvent tipEvent) throws Exception {
                        if (!view.getClass().getName().equals(tipEvent.getClazzName())){
                            return;
                        }

                        if (tipEvent.getAction() == TipEvent.TipAction.DISMISS){
                            view.dismissTip();
                            return;
                        }

                        if (tipEvent.getIconType() == -1){
                            view.showTip(tipEvent.getTipWord());
                            return;
                        }

                        view.showTip(tipEvent.getIconType(), tipEvent.getTipWord());
                    }
                }));
    }
}
