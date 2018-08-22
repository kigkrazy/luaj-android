package com.reizx.luaj.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.blankj.utilcode.util.ResourceUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.luaj.R;
import com.reizx.luaj.contract.SettingContract;
import com.reizx.luaj.presenter.SettingPresenter;
import com.reizx.luaj.util.LogUtil;
import com.reizx.luaj.util.RxUtil;
import com.reizx.luaj.view.common.BaseFragment;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingContract.View {
    Globals globals;
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.tv_setting_page_show_ip_des)
    TextView tvIpStatus;
    Disposable ds;

    @SuppressLint("CheckResult")
    @OnClick(R.id.btn_setting_page_test)
    public void clickTest() {
        if (ds != null && !ds.isDisposed()) {
            LogUtil.d("dispose the subscribe...");
            ds.dispose();
            ds = null;
            return;
        }

        LogUtil.d("click setting page test");
        ds = Flowable.interval(1, TimeUnit.SECONDS)
                .onBackpressureDrop()
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtil.d("the inter ... " + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.d("flow err : " + throwable);
                    }
                });
    }

    @OnClick(R.id.btn_setting_page_xlog)
    public void printXlog() {
        LogUtil.d("start exec ...");
        String sc_path = "/sdcard/SimpleExample.lua";
        boolean copyResult = ResourceUtils.copyFileFromAssets("SimpleExample.lua", sc_path);
        if (!copyResult){
            LogUtil.d("copy script error ...");
        }
        globals.loadfile(sc_path).invoke();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globals = JsePlatform.standardGlobals();
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initAllMembersView() {
        super.initAllMembersView();
        initTopBar();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().Inject(this);
    }

    public void initTopBar() {
        mTopBar.setTitle("设置");
    }

    @Override
    public void showIpStatus(String msg) {
        tvIpStatus.setText(msg);
    }
}
