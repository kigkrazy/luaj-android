package com.reizx.luaj.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.reizx.luaj.R;
import com.reizx.luaj.contract.MainContract;
import com.reizx.luaj.presenter.MainPresenter;
import com.reizx.luaj.view.common.BaseFragment;

import java.util.HashMap;

import butterknife.BindView;

public class MainFragment extends BaseFragment<MainPresenter> implements MainContract.View {
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabs;
    private HashMap<Pager, Fragment> mPages = new HashMap<Pager, Fragment>();

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_main;
    }

    @Override
    public void initAllMembersView() {
        initTabs();//初始化tab以及对应界面
    }

    @Override
    protected void initInject() {
        getFragmentComponent().Inject(this);
    }

    public void initTabs(){
        QMUITabSegment.Tab homeTab = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.drawable.ic_home_normal),
                ContextCompat.getDrawable(getContext(), R.drawable.ic_home_select),
                "主页", false
        );

        QMUITabSegment.Tab myTab = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.drawable.ic_setting_normal),
                ContextCompat.getDrawable(getContext(), R.drawable.ic_setting_select),
                "设置", false
        );

        mTabs.addTab(homeTab)
                .addTab(myTab);

        //region 初始化ViewPage
        mPages.put(Pager.HOME, new HomeFragment());
        mPages.put(Pager.SETTING, new SettingFragment());
        MainPageAdapter mainPageAdapter = new MainPageAdapter(getChildFragmentManager(), mPages);//解决多层嵌套fragment，在fragment切换时候空白问题
        //MainPageAdapter mainPageAdapter = new MainPageAdapter(getFragmentManager(), mPages);
        mViewPager.setAdapter(mainPageAdapter);
        //endregion

        mTabs.setupWithViewPager(mViewPager, false);
    }


    /**
     * 配合ViewPage使用的适配器
     */
    class MainPageAdapter extends FragmentPagerAdapter {
        HashMap<Pager, Fragment> mPages;
        public MainPageAdapter(FragmentManager fm, HashMap<Pager, Fragment> pages) {
            super(fm);
            this.mPages = pages;
        }

        @Override
        public Fragment getItem(int position) {
            return mPages.get(Pager.getPagerFromPositon(position));
        }

        @Override
        public int getCount() {
            return mPages.size();
        }
    }

    enum Pager {
        HOME, //home界面标识符
        SETTING;//设置界面标识符

        public static Pager getPagerFromPositon(int position) {
            switch (position) {
                case 0:
                    return HOME;
                case 1:
                    return SETTING;
                default:
                    return HOME;
            }
        }
    }
}
