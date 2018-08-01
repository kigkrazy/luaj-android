package com.reizx.luaj.view.fragment;

import android.os.IBinder;
import android.os.RemoteException;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.luaj.IAndromedaInf;
import com.reizx.luaj.R;
import com.reizx.luaj.contract.HomeConstract;
import com.reizx.luaj.presenter.HomePresenter;
import com.reizx.luaj.view.common.BaseFragment;

import org.qiyi.video.svg.Andromeda;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeConstract.View {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.tv_app_show_ip_des)
    TextView tvIp;

    @OnClick(R.id.btn_app_start_service)
    public void startZkService() {
        presenter.startZkService(baseActivity);
    }

    @OnClick(R.id.btn_app_stop_service)
    public void stopZkService() {
        presenter.stopZkService(baseActivity);
    }

    @OnClick(R.id.btn_app_request_ip)
    public void requestIp() {
        presenter.showCurrentIp();
    }

    @OnClick(R.id.btn_app_andromeda_call)
    public void andromedaCall() {
        IBinder binder = Andromeda.with(app).getRemoteService(IAndromedaInf.class);
        if (binder == null) {
            return;
        }
        IAndromedaInf andromedaInf = IAndromedaInf.Stub.asInterface(binder);
        if (andromedaInf == null) {
            return;
        }
        try {
            andromedaInf.remoteCall();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_home;
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
        mTopBar.setTitle("主页");
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
    public void setCurrentIp(String ip) {
        tvIp.setText(ip);
    }
}
