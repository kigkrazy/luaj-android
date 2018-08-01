package com.reizx.luaj.di.component;

import android.app.Activity;

import com.reizx.luaj.di.module.FragmentModule;
import com.reizx.luaj.di.scope.FragmentScope;
import com.reizx.luaj.view.fragment.HomeFragment;
import com.reizx.luaj.view.fragment.MainFragment;
import com.reizx.luaj.view.fragment.SettingFragment;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void Inject(HomeFragment homeFragment);

    void Inject(SettingFragment settingFragment);

    void Inject(MainFragment settingFragment);
}
